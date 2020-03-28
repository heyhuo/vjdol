package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.TopicAnswer;
import com.valcano.vjdol.mapper.TopicAnswerMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TopicAnswerDao implements TopicAnswerMapper {

    private TopicAnswer topicAnswer = new TopicAnswer();

    /**
     * 插入题目答案
     *
     * @param stu_no
     * @param topic_id
     */
    @Override
    public void insertTopicAnswer(String stu_no, Integer topic_id, String code) {
        SqlSession session = null;
        TopicAnswer topicAnswer = new TopicAnswer();
        topicAnswer.setStu_no(stu_no);
        topicAnswer.setTopic_id(topic_id);
        topicAnswer.setCode(code);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertTopicAnswer", topicAnswer);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }

    }

    /**
     * 通过学号题号查询答案
     *
     * @param stuNo
     * @param topicId
     * @return
     */
    @Override
    public TopicAnswer queryAnswerByStuNoTopicId(String stuNo, Integer topicId) {
        SqlSession session = null;
        topicAnswer.setStu_no(stuNo);
        topicAnswer.setTopic_id(topicId);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            topicAnswer = session.selectOne("queryAnswerByStuNoTopicId", topicAnswer);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return topicAnswer;
    }

    /**
     * 更新答案代码
     *
     * @param stuNo
     * @param topicId
     * @param code
     */
    @Override
    public void updateAnswerCode(String stuNo, Integer topicId, String code, int submitNum) {
        SqlSession session = null;
        topicAnswer.setStu_no(stuNo);
        topicAnswer.setTopic_id(topicId);
        topicAnswer.setCode(code);
        topicAnswer.setSubmit_num(submitNum);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateAnswerCode", topicAnswer);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    /**
     * 更新总分
     *
     * @param stuNo
     * @param topicId
     * @param totalScore
     */
    @Override
    public void updateTopicScore(String stuNo, Integer topicId, int totalScore) {
        SqlSession session = null;
        topicAnswer.setStu_no(stuNo);
        topicAnswer.setTopic_id(topicId);
        topicAnswer.setTopic_socre(totalScore);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicScore", topicAnswer);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    /**
     * 查询所有答案
     *
     * @return
     */
    public List<TopicAnswer> queryAllAnswer() {
        SqlSession session = null;
        List<TopicAnswer> topicAnswers = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            topicAnswers = session.selectList("queryAllAnswer");

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return topicAnswers;
    }

}
