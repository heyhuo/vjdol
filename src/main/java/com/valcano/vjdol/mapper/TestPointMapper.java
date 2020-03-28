package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.TestPoint;

import java.util.List;

public interface TestPointMapper {

    /**
     * 插入测试点
     *
     * @param topicId
     * @param pointId
     */
    void isnertTestPoint(Integer topicId, Integer pointId);

    /**
     * 查询测试点
     *
     * @param topic_id
     * @param point_id
     */
    TestPoint queryPointById(Integer topic_id, Integer point_id);

    void deleteTestPointById(Integer topic_id, Integer point_id);

    /**
     * 查询题目中所有的测试点
     *
     * @param topic_id
     * @return
     */
    List<TestPoint> queryPoint(Integer topic_id);

    void deleteTestPointAll(Integer topic_id);

    void updateInputDataById(Integer topic_id, Integer point_id, String input_data, String path);

    void updateOutDataById(Integer topic_id, Integer point_id, String out_data);


}
