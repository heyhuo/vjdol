package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.ExamPaper;

import java.util.List;

public interface ExamPaperMapper {
    /**
     * 插入试卷
     *
     * @param stu_no
     * @param topic_id
     */
    void insertExamPaper(String stu_no, int topic_id);

    /**
     * 通过学号、题号、点号查询
     *
     * @param stu_no
     * @param topic_id
     * @param point_id
     * @return
     */
    ExamPaper queryExamByStuNoTopicIdPointId(String stu_no, int topic_id, int point_id);


    /**
     * 通过学号、题号查询
     *
     * @param stu_no
     * @param topic_id
     * @return
     */
    List<ExamPaper> queryExamByStuNoTopicId(String stu_no, int topic_id);

    /**
     * 更新运行结果
     *
     * @param topicId
     * @param stuNo
     * @param i
     * @param result
     */
    void updateResult(Integer topicId, String stuNo, int i, String result);

    /**
     * 插入程序运行结果
     *
     * @param topicId
     * @param stuNo
     * @param i
     * @param result
     */
    void insertExamPaperResult(Integer topicId, String stuNo, int i, String result);

    /**
     * 插入评判结果
     *
     * @param stu_no
     * @param topic_id
     * @param point_id
     * @param judge_result
     */
    void updateJudgeResult(String stu_no, Integer topic_id, int point_id, String judge_result, int point_score);


    /**
     * 通过学号题号插入试卷测试点信息
     *
     * @param stuNo
     * @param topicId
     * @param pointId
     */
    void insertExamPaperPoint(String stuNo, Integer topicId, int pointId);

    /**
     * 更新题目测试点运行时间
     *
     * @param stuNo
     * @param topicId
     * @param pointId
     * @param time
     */
    void updateExamPaperTime(String stuNo, int topicId, int pointId, long time);

    /**
     * 删除
     *
     * @param topicId
     * @param stuNo
     */
    void deletePaperByTopicIdStuNo(Integer topicId, String stuNo);


    void updateResult(int i, String s, String compile_error,String judgeResult);
}
