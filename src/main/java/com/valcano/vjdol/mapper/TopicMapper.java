package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.Topic;

import java.util.List;

public interface TopicMapper {

    /**
     * 通过id插入题目
     *
     * @param topic_Id
     */
    void insertTopic(int topic_Id);

    /**
     * 更新标题
     *
     * @param title
     * @param id
     */
    void updateTopicTitle(String title, int id);

    /**
     * 更新内容
     *
     * @param content
     * @param id
     */
    void updateTopicContent(String content, int id);

    /**
     * 更新输入格式
     *
     * @param inputFormat
     * @param id
     */
    void updateTopicInputFormat(String inputFormat, Integer id);

    /**
     * 更新输出格式
     *
     * @param outputFormat
     * @param id
     */
    void updateTopicOutputFormat(String outputFormat, Integer id);

    /**
     * 更新输入样例
     *
     * @param inputSample
     * @param id
     */
    void updateTopicInputSample(String inputSample, Integer id);

    /**
     * 更新输出样例
     *
     * @param outputSample
     * @param id
     */
    void updateTopicOutputSample(String outputSample, Integer id);

    /**
     * 返回题目列表
     *
     * @return
     */
    List<Topic> queryAllTopic();

    /**
     * 通过id删除题目
     *
     * @param id
     */
    void deleteTopicById(Integer id);

    /**
     * 通过id查询题目
     *
     * @param id
     * @return
     */
    Topic quryTopicById(Integer id);

    /**
     * 通过id修改发布状态
     *
     * @param id
     * @param state
     */
    void editState(Integer id, String state);

    /**
     * 更新运行时限
     *
     * @param id
     * @param time_limit
     */
    void updateTopicTimeLimitById(Integer id, Integer time_limit);

    //更新内存限制
    void updateTopicMemoryLimitById(Integer id, Integer memory_limit);

    /**
     * 更新题目分数
     *
     * @param id
     * @param topic_score
     */
    void updateTopicScoreById(Integer id, Integer topic_score);

    /**
     * 更新题目测试点数
     *
     * @param topic_id
     * @param point_num
     */
    void updateTopicPointNum(Integer topic_id, Integer point_num);


    /**
     * 查询已发布的题目
     *
     * @return
     */
    List<Topic> queryPublishTopic();

    /**
     * 更新题目提交数
     *
     * @param topicId
     * @param submit
     */
    void updateTotalSubmit(Integer topicId, int submit);

    /**
     * 更新题目通过数
     *
     * @param topicId
     * @param accepted
     */
    void updateTopicAccepted(Integer topicId, int accepted);
}
