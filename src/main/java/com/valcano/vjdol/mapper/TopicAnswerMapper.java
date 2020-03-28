package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.TopicAnswer;

public interface TopicAnswerMapper {

    /**
     * 插入答案
     *
     * @param stu_no
     * @param topic_id
     */
    void insertTopicAnswer(String stu_no, Integer topic_id, String code);

    /**
     * 通过学号题号查询答案
     *
     * @param stuNo
     * @param topicId
     */
    TopicAnswer queryAnswerByStuNoTopicId(String stuNo, Integer topicId);

    /**
     * 更新题目代码
     *
     * @param stuNo
     * @param topicId
     * @param code
     */
    void updateAnswerCode(String stuNo, Integer topicId, String code, int submit_num);

    /**
     * 更新总分
     *
     * @param stuNo
     * @param topicId
     * @param totalScore
     */
    void updateTopicScore(String stuNo, Integer topicId, int totalScore);
}
