package com.valcano.vjdol.controller;

import com.alibaba.fastjson.JSONObject;
import com.valcano.vjdol.dao.ExamPaperDao;
import com.valcano.vjdol.dao.TestPointDao;
import com.valcano.vjdol.dao.TopicAnswerDao;
import com.valcano.vjdol.dao.TopicDao;
import com.valcano.vjdol.entity.ExamPaper;
import com.valcano.vjdol.entity.TestPoint;
import com.valcano.vjdol.entity.Topic;
import com.valcano.vjdol.entity.TopicAnswer;
import com.valcano.vjdol.judger.FileUtil;
import com.valcano.vjdol.judger.JudeCode;
import com.valcano.vjdol.mapper.ExamPaperMapper;
import com.valcano.vjdol.mapper.TopicAnswerMapper;
import com.valcano.vjdol.mapper.TopicMapper;
import com.valcano.vjdol.sandBox.SandBox;
import com.valcano.vjdol.sandBox.dto.Problem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

@RestController
public class JudgerController {

    ExamPaperMapper paperDao = new ExamPaperDao();
    TopicAnswerMapper answerDao = new TopicAnswerDao();
    TopicMapper topicDao = new TopicDao();
    TestPointDao pointDao = new TestPointDao();

    /**
     * 提交代码
     *
     * @param params
     */
    @RequestMapping(value = "/submitCode")
    @ResponseBody
    public Object submitCode(@RequestBody JSONObject params) {

        Integer topicId = params.getInteger("topicId");
        String stuNo = params.getString("stuNo");
        String code = params.getString("code");


        //获取题目
        Topic topic = topicDao.quryTopicById(topicId);

        //测试点数
        int point_num = topic.getPoint_num();

        //获取题目提交数
        int submit = topic.getTotal_submit();

        //构建题目对象
        Problem problem = new Problem();
        problem.setRunId(topicId);
        problem.setStuNo(stuNo);
        problem.setCode(code);
        problem.setTimeLimit(topic.getTime_limit());
        problem.setMemoryLimit(topic.getMemory_limit());
        TestPointDao pointDao = new TestPointDao();
        List<String> path = pointDao.queryPointInputPath(problem.getRunId());

        problem.setInputDataFilePathList(path);

        //更新提交数
        topicDao.updateTotalSubmit(topicId, submit + 1);

        //试卷表学生信息不存在时插入
        for (int pointId = 1; pointId <= point_num; pointId++) {
            if (paperDao.queryExamByStuNoTopicIdPointId(stuNo, topicId, pointId) == null)
                paperDao.insertExamPaperPoint(stuNo, topicId, pointId);
        }

        //答案表学生题目信息不存在时插入
        TopicAnswer topicAnswer = answerDao.queryAnswerByStuNoTopicId(stuNo, topicId);
        if (topicAnswer == null) {
            answerDao.insertTopicAnswer(stuNo, topicId, code);
        }
        //更新答案代码
        else {
            int submit_num = topicAnswer.getSubmit_num();
            answerDao.updateAnswerCode(stuNo, topicId, code, submit_num + 1);
        }

        //输入数据路径
        String inDataPath = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/in/";
        //输出数据路径
        String outDataPath = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/stuOut/";

        //调用提交代码函数
        JudeCode judger = new JudeCode();

        FileUtil fileUtil = new FileUtil();

        //沙箱模型启动
        new SandBox(problem);

//        for (int i = 1; i <= point_num; i++) {
//            String in = inDataPath + topicId + "_" + i + ".in";
//            String out = outDataPath + topicId + "_" + i + "_stu_" + stuNo + ".out";
//            judger.checkSubmitCode(code, in, out, topicId, stuNo, i);
//        }

        Map<String, Object> map = new HashMap<>();
        map.put("message", "/pendingResult.html");
        return map;
    }

    /**
     * 将运行结果写入数据库
     *
     * @param params
     * @return
     */
//    @RequestMapping(value = "/readFile")
//    @ResponseBody
//    public Object readFile(@RequestBody JSONObject params) {
//        Integer topicId = params.getInteger("topicId");
//        String stuNo = params.getString("stuNo");
//
//        //读取文件路径
//        String outDataPath = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/stuOut/";
//
//        //获取测试点数
//        int point_num = topicDao.quryTopicById(topicId).getPoint_num();
//
//        FileUtil fileUtil = new FileUtil();
//        //将学生结果写入数据库
//        for (int i = 1; i <= point_num; i++) {
//            String out = outDataPath + topicId + "_" + i + "_stu_" + stuNo + ".out";
//            String result = fileUtil.readFile(out);
//            if (paperDao.queryExamByStuNoTopicIdPointId(stuNo, topicId, i) != null)
//                paperDao.updateResult(topicId, stuNo, i, result);
//            else
//                paperDao.insertExamPaperResult(topicId, stuNo, i, result);
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", "错误");
//        return map;
//    }


    /**
     * 评判运行结果
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/judgeResult")
    @ResponseBody
    public Object judgeResult(@RequestBody JSONObject params) throws InterruptedException {
        Integer topicId = params.getInteger("topicId");
        String stuNo = params.getString("stuNo");
        //获取题目测试点
        List<TestPoint> points = pointDao.queryPoint(topicId);

        Topic topic = topicDao.quryTopicById(topicId);
        //运行时间限制
        int time = topic.getTime_limit();
        //分数
        int score = topic.getTopic_score();
        //总分
        int totalScore = 0;
        int grade = 0;
        sleep(700);
        for (int i = 0; i < points.size(); i++) {
            String out = points.get(i).getOut_data().trim();
            //获取学生题目测试点运行结果
            ExamPaper answer = paperDao.queryExamByStuNoTopicIdPointId(stuNo, topicId, i + 1);

            String result = answer.getResult().trim();

            //运行时间
            int resultTime = (int) answer.getTime_limit();
            System.out.println("程序时间->" + resultTime);

            //点数
            int num = topic.getPoint_num();
            int pointGrade = 0;
            String judge_result = "";
            if (result == null)
                result = "";
            if (out.equals(result)) {
                judge_result = "答案正确";
                if (i == points.size() - 1) {
                    pointGrade = score - grade;
                } else {
                    pointGrade = score / num;
                }
            } else if (result.equals("compile error")) {
                judge_result = "编译错误";
            } else if (result.equals("segment error")) {
                judge_result = "段错误";
            } else if (result.equals("time out")) {
                judge_result = "运行超时";
            } else if (result.equals("memory out")) {
                judge_result = "内存溢出";
            } else {
                judge_result = "答案错误";
            }
            grade += score / num;
            totalScore += pointGrade;

            paperDao.updateJudgeResult(stuNo, topicId, i + 1, judge_result, pointGrade);
        }
        //获取通过数
        int accNum = topicDao.quryTopicById(topicId).getAccepted();
        if (totalScore == score)
            topicDao.updateTopicAccepted(topicId, accNum + 1);
        answerDao.updateTopicScore(stuNo, topicId, totalScore);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "评判完成");
        return map;
    }


    /**
     * 查询评判结果
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryJudgeResultByid")
    @ResponseBody
    public Object queryJudgeResultByid(@RequestBody JSONObject params) {
        Integer topicId = params.getInteger("topicId");
        String stuNo = params.getString("stuNo");
        //获取题目测试点
        boolean flag;
        List<ExamPaper> examPapers = paperDao.queryExamByStuNoTopicId(stuNo, topicId);
        if (examPapers == null) flag = false;
        else flag = true;

        Map<String, Object> map = new HashMap<>();
        map.put("message", examPapers);
        map.put("success", flag);
        return map;
    }

}
