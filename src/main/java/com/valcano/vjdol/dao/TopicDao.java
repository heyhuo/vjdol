package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.Topic;
import com.valcano.vjdol.mapper.TopicMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TopicDao implements TopicMapper {

    @Override
    public void insertTopic(int topic_Id) {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertTopic", topic_Id);

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

    @Override
    public void updateTopicTitle(String title, int id) {
        Topic topic = new Topic();
        topic.setTopic_title(title);
        topic.setTopic_id(id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicTitle", topic);

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

    @Override
    public void updateTopicContent(String content, int id) {
        Topic topic = new Topic();
        topic.setTopic_content(content);
        topic.setTopic_id(id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicContent", topic);

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

    @Override
    public void updateTopicInputFormat(String inputFormat, Integer id) {
        Topic topic = new Topic();
        topic.setInput_format(inputFormat);
        topic.setTopic_id(id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicInputFormat", topic);

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

    @Override
    public void updateTopicOutputFormat(String outputFormat, Integer id) {
        Topic topic = new Topic();
        topic.setOutput_format(outputFormat);
        topic.setTopic_id(id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicOutputFormat", topic);

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

    @Override
    public void updateTopicInputSample(String inputSample, Integer id) {
        Topic topic = new Topic();
        topic.setInput_sample(inputSample);
        topic.setTopic_id(id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicInputSample", topic);

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

    @Override
    public void updateTopicOutputSample(String outputSample, Integer id) {
        Topic topic = new Topic();
        topic.setOutput_sample(outputSample);
        topic.setTopic_id(id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTopicOutputSample", topic);

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
     * 查询所有题目
     *
     * @return
     */
    @Override
    public List<Topic> queryAllTopic() {
        SqlSession session = null;
        List<Topic> topicList = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            topicList = session.selectList("queryAllTopic");

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return topicList;
    }

    /**
     * 查询已发布的题目
     *
     * @return
     */
    @Override
    public List<Topic> queryPublishTopic() {
        SqlSession session = null;
        List<Topic> topicList = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            topicList = session.selectList("queryPublishTopic");

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return topicList;
    }

    /**
     * 更新题目提交数
     *
     * @param topicId
     * @param submit
     */
    @Override
    public void updateTotalSubmit(Integer topicId, int submit) {
        Topic topic = new Topic();
        topic.setTotal_submit(submit);
        topic.setTopic_id(topicId);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateTotalSubmit", topic);

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
     * 更新题目提交数
     *
     * @param topicId
     * @param accepted
     */
    @Override
    public void updateTopicAccepted(Integer topicId, int accepted) {
        Topic topic = new Topic();
        topic.setTopic_id(topicId);
        topic.setAccepted(accepted);

        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateAccepted", topic);

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

    @Override
    public void deleteTopicById(Integer id) {
        SqlSession session = null;
        List<Topic> topicList = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.delete("deleteTopicById", id);

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

    @Override
    public Topic quryTopicById(Integer id) {
        SqlSession session = null;
        Topic topic = new Topic();
        topic.setTopic_id(-1);
        Topic q = new Topic();
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            q = session.selectOne("quryTopicById", id);
            if (q != null) {
                topic = q;
            }
            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return topic;
    }

    @Override
    public void editState(Integer id, String state) {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();
            //2、执行命令
            if (state.equals("true"))
                session.update("editTrueState", id);
            else
                session.update("editFalseState", id);

            //3、SqlSession提交
            session.commit();

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }

    }

    @Override
    public void updateTopicTimeLimitById(Integer id, Integer time_limit) {
        SqlSession session = null;
        Topic topic = new Topic();
        topic.setTopic_id(id);
        topic.setTime_limit(time_limit);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();
            //2、执行命令
            session.update("updateTopicTimeLimitById", topic);

            //3、SqlSession提交
            session.commit();

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    @Override
    public void updateTopicMemoryLimitById(Integer id, Integer memory_limit) {
        SqlSession session = null;
        Topic topic = new Topic();
        topic.setTopic_id(id);
        topic.setMemory_limit(memory_limit);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();
            //2、执行命令
            session.update("updateTopicMemoryLimitById", topic);

            //3、SqlSession提交
            session.commit();

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    @Override
    public void updateTopicScoreById(Integer id, Integer topic_score) {
        SqlSession session = null;
        Topic topic = new Topic();
        topic.setTopic_id(id);
        topic.setTopic_score(topic_score);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();
            //2、执行命令
            session.update("updateTopicScoreById", topic);

            //3、SqlSession提交
            session.commit();

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    @Override
    public void updateTopicPointNum(Integer topic_id, Integer point_num) {
        SqlSession session = null;
        Topic topic = new Topic();
        topic.setTopic_id(topic_id);
        topic.setPoint_num(point_num);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();
            //2、执行命令
            session.update("updateTopicPointNum", topic);

            //3、SqlSession提交
            session.commit();

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }


}
