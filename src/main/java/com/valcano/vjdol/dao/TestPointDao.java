package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.TestPoint;
import com.valcano.vjdol.mapper.TestPointMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestPointDao implements TestPointMapper {
    TopicDao dao = new TopicDao();

    /**
     * 插入测试点
     *
     * @param topicId
     * @param pointId
     */
    @Override
    public void isnertTestPoint(Integer topicId, Integer pointId) {
        SqlSession session = null;
        TestPoint testPoint = new TestPoint();
        testPoint.setPoint_id(pointId);
        testPoint.setTopic_id(topicId);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertTestPoint", testPoint);

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
     * 通过id查询测试点
     *
     * @param topic_id
     * @param point_id
     * @return
     */
    @Override
    public TestPoint queryPointById(Integer topic_id, Integer point_id) {
        SqlSession session = null;
        TestPoint testPoint = new TestPoint();
        TestPoint q = new TestPoint();
        testPoint.setPoint_id(point_id);
        testPoint.setTopic_id(topic_id);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            q = session.selectOne("queryPointById", testPoint);
            if (q == null)
                testPoint.setPoint_id(-1);
            else
                testPoint = q;
            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return testPoint;
    }

    /**
     * 通过id删除测试点
     *
     * @param topic_id
     * @param point_id
     */
    @Override
    public void deleteTestPointById(Integer topic_id, Integer point_id) {
        SqlSession session = null;
        TestPoint testPoint = new TestPoint();
        testPoint.setPoint_id(point_id);
        testPoint.setTopic_id(topic_id);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.delete("deleteTestPointById", testPoint);

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
    public List<TestPoint> queryPoint(Integer topic_id) {
        List<TestPoint> testPoints = null;
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            testPoints = session.selectList("queryPoint", topic_id);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return testPoints;
    }

    public List<String> queryPointInputPath(Integer topic_id) {
        List<String> inputPath = null;
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            inputPath = session.selectList("queryPointInputPath", topic_id);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return inputPath;
    }

    /**
     * 更新测试点分数
     *
     * @param topic_id
     * @param point_id
     */
    public void updatePointScore(int topic_id, int point_id, int point_score) {
        SqlSession session = null;
        TestPoint testPoint = new TestPoint();
        testPoint.setTopic_id(topic_id);
        testPoint.setPoint_id(point_id);
        testPoint.setPoint_socre(point_score);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updatePointScoreById", testPoint);

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
    public void deleteTestPointAll(Integer topic_id) {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.delete("deleteTestPointAll", topic_id);

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
    public void updateInputDataById(Integer topic_id, Integer point_id, String input_data, String path) {
        SqlSession session = null;
        TestPoint testPoint = new TestPoint();
        testPoint.setPoint_id(point_id);
        testPoint.setTopic_id(topic_id);
        testPoint.setInput_data(input_data);
        testPoint.setInput_path(path);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateInputDataById", testPoint);

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
    public void updateOutDataById(Integer topic_id, Integer point_id, String out_data) {
        SqlSession session = null;
        TestPoint testPoint = new TestPoint();
        testPoint.setPoint_id(point_id);
        testPoint.setTopic_id(topic_id);
        testPoint.setOut_data(out_data);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateOutDataById", testPoint);

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


}