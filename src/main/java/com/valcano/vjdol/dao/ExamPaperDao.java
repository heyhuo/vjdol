package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.ExamPaper;
import com.valcano.vjdol.entity.TopicAnswer;
import com.valcano.vjdol.mapper.ExamPaperMapper;
import org.apache.ibatis.session.SqlSession;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExamPaperDao implements ExamPaperMapper {
    private ExamPaper examPaper = new ExamPaper();

    /**
     * 插入试卷
     *
     * @param stu_no
     * @param topic_id
     */
    @Override
    public void insertExamPaper(String stu_no, int topic_id) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stu_no);
        examPaper.setTopic_id(topic_id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertExamPaper", examPaper);

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
     * 通过学号、题号、测试点号查询
     *
     * @param stu_no
     * @param topic_id
     * @param point_id
     * @return
     */
    @Override
    public ExamPaper queryExamByStuNoTopicIdPointId(String stu_no, int topic_id, int point_id) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setTopic_id(topic_id);
        examPaper.setStu_no(stu_no);
        examPaper.setPoint_id(point_id);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            examPaper = session.selectOne("queryExamByStuNoTopicIdPointId", examPaper);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return examPaper;

    }

    /**
     * 通过学号、题号查询
     *
     * @param stu_no
     * @param topic_id
     * @return
     */
    @Override
    public List<ExamPaper> queryExamByStuNoTopicId(String stu_no, int topic_id) {
        List<ExamPaper> examPapers = null;
        examPaper.setTopic_id(topic_id);
        examPaper.setStu_no(stu_no);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            examPapers = session.selectList("queryExamByStuNoTopicId", examPaper);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return examPapers;

    }

    /**
     * 更新运行结果
     *
     * @param topicId
     * @param stuNo
     * @param pointId
     * @param result
     */
    @Override
    public void updateResult(Integer topicId, String stuNo, int pointId, String result) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stuNo);
        examPaper.setTopic_id(topicId);
        examPaper.setResult(result);
        examPaper.setPoint_id(pointId);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("updateResult", examPaper);

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
     * 插入程序运行结果
     */
    @Override
    public void insertExamPaperResult(Integer topicId, String stuNo, int pointId, String result) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stuNo);
        examPaper.setTopic_id(topicId);
        examPaper.setResult(result);
        examPaper.setPoint_id(pointId);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertExamPaperResult", examPaper);

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
     * 更新评判结果
     *
     * @param stu_no
     * @param topic_id
     * @param point_id
     * @param judge_result
     */
    @Override
    public void updateJudgeResult(String stu_no, Integer topic_id, int point_id, String judge_result, int point_score) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stu_no);
        examPaper.setTopic_id(topic_id);
        examPaper.setPoint_id(point_id);
        examPaper.setJudge_result(judge_result);
        examPaper.setPoint_grade(point_score);

        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("updateJudgeResult", examPaper);

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
     * 通过学号题号插入试卷测试点信息
     *
     * @param stuNo
     * @param topicId
     * @param pointId
     */
    @Override
    public void insertExamPaperPoint(String stuNo, Integer topicId, int pointId) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stuNo);
        examPaper.setTopic_id(topicId);
        examPaper.setPoint_id(pointId);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertExamPaperPoint", examPaper);

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
     * 更新题目测试点运行时间
     *
     * @param stuNo
     * @param topicId
     * @param pointId
     * @param time
     */
    @Override
    public void updateExamPaperTime(String stuNo, int topicId, int pointId, long time) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stuNo);
        examPaper.setTopic_id(topicId);
        examPaper.setPoint_id(pointId);
        examPaper.setTime_limit(time);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("updateExamPaperTime", examPaper);

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
     * 删除
     *
     * @param topicId
     * @param stuNo
     */
    @Override
    public void deletePaperByTopicIdStuNo(Integer topicId, String stuNo) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stuNo);
        examPaper.setTopic_id(topicId);
        TopicAnswer answer = new TopicAnswer();
        answer.setTopic_id(topicId);
        answer.setStu_no(stuNo);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            session.delete("deletePaperByTopicIdStuNo", examPaper);

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
    public void updateResult(int topicId, String stuNo, String result, String judgeResult) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStu_no(stuNo);
        examPaper.setTopic_id(topicId);
        examPaper.setResult(result);
        examPaper.setJudge_result(judgeResult);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateErrorResult", examPaper);

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

    //    @Override
    public void updateAllResult(int topicId, String stuNo, int point_id, long time, long memory, String result) {
        SqlSession session = null;
        ExamPaper examPaper = new ExamPaper(stuNo, topicId, point_id, time, memory, result);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateAllResult", examPaper);

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
