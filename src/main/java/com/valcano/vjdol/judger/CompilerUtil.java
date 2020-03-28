package com.valcano.vjdol.judger;

import com.valcano.vjdol.dao.ExamPaperDao;

import javax.tools.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompilerUtil {
    private ExamPaperDao examPaperDao = new ExamPaperDao();

    //动态编译执行函数
    public Object CompilerCode(String code) {

        // 1、通过JavaCompiler动态编译
        //JavaCompiler: 负责读取源代码，编译诊断，输出class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();

        //JavaFileManager: 管理JavaFileObject，负责JavaFileObject的创建和保存位置
        JavaFileManager javaFileManager = new JudgerUtil.MyJavaFileManager(compiler.getStandardFileManager(collector, null, null));


        //配置编译选项
        List<String> options = new ArrayList<>();
        options.add("-target");
        options.add("1.8");

        //编译模式的正则表达式
        Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
        //匹配代码
        Matcher matcher = CLASS_PATTERN.matcher(code);
        //代码类名
        String clssName = "";

        //正则匹配class 后的名称
        if (matcher.find()) {
            clssName = matcher.group(1);

        } else {
            System.out.println("compile error");
            throw new IllegalArgumentException("No such class name in " + clssName);
        }

        //JavaFileObject: 文件抽象，代表源代码或者编译后的class
        //将code编译成classname.java
        JavaFileObject javaFileObject = new JudgerUtil.MyJavaFileObject(clssName, code);

        //编译
        JavaCompiler.CompilationTask task = compiler
                .getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject));

        Map<String, Object> map = new HashMap<>();
        map.put("task", task);
        map.put("className", clssName);

        return map;
    }
}
