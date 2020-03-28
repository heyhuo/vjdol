package com.valcano.vjdol.sandBox;

import com.valcano.vjdol.constant.CommunicationSignal;
import com.valcano.vjdol.dao.ExamPaperDao;
import com.valcano.vjdol.dao.TestPointDao;
import com.valcano.vjdol.entity.ExamPaper;
import com.valcano.vjdol.judger.FileUtil;
import com.valcano.vjdol.sandBox.callable.ProblemCallable;
import com.valcano.vjdol.sandBox.classLoader.SandboxClassLoader;
import com.valcano.vjdol.sandBox.dto.Problem;
import com.valcano.vjdol.sandBox.dto.ProblemResult;
import com.valcano.vjdol.sandBox.dto.ProblemResultItem;
import com.valcano.vjdol.sandBox.dto.SandboxInitData;
import com.valcano.vjdol.sandBox.systemStream.CacheOutputStream;
import com.valcano.vjdol.sandBox.systemStream.ThreadInputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;


public class SandBox {
    private String pid;
    private SandboxClassLoader sandboxClassLoader;
    private long beginStartTime;
    private CacheOutputStream resultBuffer = new CacheOutputStream();
    private ThreadInputStream systemThreadIn = new ThreadInputStream();
    private ProblemCallable problemCallable;
    private int loadClassCount = 0;
    private boolean isBusy = false;
    ExamPaperDao paperDao = new ExamPaperDao();
    TestPointDao pointDao = new TestPointDao();

    // 用一个线程池去处理每个判题请求
    private ExecutorService problemThreadPool = Executors
            .newSingleThreadExecutor(new ThreadFactory() {

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("problemThreadPool");
                    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                        @Override
                        public void uncaughtException(Thread t, Throwable e) {

                        }
                    });
                    return thread;
                }
            });

    // 用一个线程池去等待每个判题请求的结果返回
    private ExecutorService problemResultThreadPool = Executors
            .newSingleThreadExecutor(new ThreadFactory() {

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("problemResultThreadPool");
                    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println("das");
                        }
                    });
                    return thread;
                }
            });

    public SandBox(Problem problem) {
        initSandbox(problem);
    }

    private void initSandbox(Problem problem) {
        // 获取进程id，用于向外界反馈
        getPid();

        //沙箱环境
        buildEnvironment(problem);

    }

    /**
     * 获取进程ID
     */
    private void getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        pid = name.split("@")[0];
    }

    /**
     * 建立沙箱环境
     *
     * @param problem
     */
    private void buildEnvironment(Problem problem) {
        //代码编译及加载
        sandboxClassLoader = new SandboxClassLoader(
                problem.getCode());

        // 获取开始时的系统时间
        beginStartTime = System.currentTimeMillis();

        // 重定向输出流
        System.setOut(new PrintStream(resultBuffer));

        // 重定向输入流
        System.setIn(systemThreadIn);

        //设置安全权限
        System.setSecurityManager(new SandboxSecurityManager(problem));

        //处理题目
        Future<List<ProblemResultItem>> processProblem = processProblem(problem);

        if (processProblem != null)
            //返回运行结果
            returnJudgedProblemResult("1", processProblem);
        else
            paperDao.updateResult(problem.getRunId(), problem.getStuNo(), "compile error", "编译错误");

        loadClassCount++;
    }


    /**
     * 进行项目处理
     *
     * @return 题目处理结果
     */
    private Future<List<ProblemResultItem>> processProblem(Problem problem) {
        try {
            Class<?> mainClass = sandboxClassLoader.loadSandboxClass();
            if (mainClass != null) {
                Method mainMethod = null;
                mainMethod = mainClass.getMethod("main", String[].class);
                if (!Modifier.isStatic(mainMethod.getModifiers()))
                    throw new Exception("main方法不是静态方法");

                mainMethod.setAccessible(true);
                problemCallable = new ProblemCallable(mainMethod, problem,
                        resultBuffer, systemThreadIn);
                Future<List<ProblemResultItem>> submit = problemThreadPool
                        .submit(problemCallable);
                isBusy = true;
                mainClass = null;

                return submit;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("编译错误");
        } catch (Exception e) {

        }
        return null;
    }


    /**
     * 返回题目运行结果
     *
     * @param signalId       信号
     * @param processProblem 题目运行结果
     */
    private void returnJudgedProblemResult(final String signalId,
                                           final Future<List<ProblemResultItem>> processProblem) {
        problemResultThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                if (processProblem != null) {
                    try {
                        List<ProblemResultItem> resultItems = processProblem
                                .get();
                        Problem problem = ProblemCallable.getProblem();
                        ProblemResult problemResult = new ProblemResult();
                        problemResult.setRunId(problem.getRunId());
                        problemResult.setResultItems(resultItems);
                        FileUtil f = new FileUtil();
                        //判题
                        for (int i = 1; i <= resultItems.size(); i++) {
                            String inpath = problemResult.getResultItems().get(i - 1).getInputFilePath();
                            int index = Integer.parseInt(inpath.substring(inpath.indexOf("_") + 1, inpath.indexOf(".")));
                            long time = problemResult.getResultItems().get(i - 1).getUseTime();
                            long memory = problemResult.getResultItems().get(i - 1).getUseMemory();
                            String result = problemResult.getResultItems().get(i - 1).getResult();
                            String message = problemResult.getResultItems().get(i - 1).getMessage();
                            if (result == null && message != null)
                                result = message;
                            else if (result == null && message == null)
                                result = "";
                            if (problem.getMemoryLimit() < memory)
                                result = "memory out";
                            else if (problem.getTimeLimit() < time)
                                result = "time out";
                            paperDao.updateAllResult(problem.getRunId(), problem.getStuNo(), index, time, memory, result);
//                            f.writeByFileWrite(problemResult.getResultItems().get(i - 1).toString(), "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/stuOut/" + index + ".txt");
                        }
                        isBusy = false;
                        problemCallable = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
