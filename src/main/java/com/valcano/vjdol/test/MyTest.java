package com.valcano.vjdol.test;

import com.valcano.vjdol.dao.*;
import com.valcano.vjdol.entity.*;
import com.valcano.vjdol.judger.JudeCode;
import com.valcano.vjdol.mapper.AdminMapper;
import com.valcano.vjdol.mapper.TopicAnswerMapper;
import com.valcano.vjdol.mapper.TopicMapper;
import org.junit.Before;
import org.junit.Test;
import sun.security.provider.MD5;

import java.io.*;
import java.util.Date;
import java.util.List;


public class MyTest {

    private TestPointDao testPointDao;
    private TopicDao topicDao;
    private StudentDao studentDao;
    private ExamPaperDao examPaperDao;
    private TopicAnswerDao topicAnswerDao;
    private TopicAnswerDao answerDao;
    private TeacherDao teacherDao;

    @Before
    public void before() {
        testPointDao = new TestPointDao();
        topicDao = new TopicDao();
        studentDao = new StudentDao();
        examPaperDao = new ExamPaperDao();
        topicAnswerDao = new TopicAnswerDao();
        answerDao = new TopicAnswerDao();
        teacherDao = new TeacherDao();
    }


    /**
     * 读取文件
     */
    @Test
    public void da() {


    }

}

