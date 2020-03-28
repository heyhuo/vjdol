package com.valcano.vjdol.controller;

import com.alibaba.fastjson.JSONObject;
import com.valcano.vjdol.dao.TestPointDao;
import com.valcano.vjdol.dao.TopicDao;
import com.valcano.vjdol.entity.TestPoint;
import com.valcano.vjdol.entity.Topic;
import com.valcano.vjdol.mapper.TestPointMapper;
import com.valcano.vjdol.mapper.TopicMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestPointController {

    TestPointMapper dao = new TestPointDao();
    TopicMapper topicDao = new TopicDao();

    /**
     * 添加题目
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/insertTestPoint")
    @ResponseBody
    public void insertTestPoint(@RequestBody JSONObject params) {
        Integer topic_id = params.getInteger("topicId");
        Integer point_id = params.getInteger("pointId");
        int flag = dao.queryPointById(topic_id, point_id).getPoint_id();
        if (flag == -1) {
            dao.isnertTestPoint(topic_id, point_id);
        } else {
            dao.deleteTestPointAll(topic_id);
            dao.isnertTestPoint(topic_id, point_id);
        }
    }


    /**
     * 删除全部及更新题目测试点数
     *
     * @param params
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public void deleteAll(@RequestBody JSONObject params) {
        Integer topic_id = params.getInteger("topicId");
        Integer point_num = params.getInteger("pointNum");
        //删除测试点
        dao.deleteTestPointAll(topic_id);
        //删除所有文件
        TopicDao topicDao = new TopicDao();
        System.out.println(topic_id);
        Topic topic = topicDao.quryTopicById(topic_id);
        int len = topic.getPoint_num();
        for (int i = 1; i <= len; i++) {
            deleteFile(topic_id, i, "in");
            deleteFile(topic_id, i, "out");
        }
        //更新测试点数
        topicDao.updateTopicPointNum(topic_id, point_num);
    }

    /**
     * 查询全部测试点
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryPoint")
    @ResponseBody
    public List<TestPoint> queryPoint(@RequestBody JSONObject params) {
        Integer topic_id = params.getInteger("topicId");
        List<TestPoint> testPointList = null;
        testPointList = dao.queryPoint(topic_id);
        for (int i = 0; i < testPointList.size(); i++) {
            if (testPointList.get(i).getInput_data() == null)
                testPointList.get(i).setInput_data("在这里预览输入数据");
            if (testPointList.get(i).getOut_data() == null)
                testPointList.get(i).setOut_data("在这里预览输出数据");
        }
        return testPointList;
    }

    /**
     * 编辑输入数据
     *
     * @param params
     */
    @RequestMapping(value = "/editInputData")
    @ResponseBody
    public void editInputData(@RequestBody JSONObject params) throws IOException {
        Integer topic_id = params.getInteger("topicId");
        Integer point_id = params.getInteger("pointId");
        String input_data = params.getString("content");

        String path = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/in/" + topic_id+"/" + topic_id + "_" + point_id + ".in";

        dao.updateInputDataById(topic_id, point_id, input_data,path);
        //把数据写入文件
        DataWriteFile(topic_id, point_id, input_data, "in");
    }

    /**
     * 删除文件
     */
    public void deleteFile(int topic_id, int point_id, String position) {
        String path = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/";
        path += position + "/" + topic_id + "/" + topic_id + "_" + point_id + "." + position;
        File file = new File(path);
        if (file.exists()) file.delete();

    }

    /**
     * 把数据写入文件
     *
     * @param topic_id
     * @param point_id
     * @param input_data
     * @throws IOException
     */
    public void DataWriteFile(int topic_id, int point_id, String input_data, String position) throws IOException {
        String path = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/";
        String dirPath = path + position + "/" + topic_id;
        path = dirPath + "/" + topic_id + "_" + point_id + "." + position;
        File dir = new File(dirPath);
        if (!dir.exists())
            dir.mkdir();
        System.out.println(dirPath);
        File destFile = new File(path);
        if (!destFile.exists()) destFile.createNewFile();
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write(input_data);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
                fw = null;
            }
        }
    }

    /**
     * 编辑输出数据
     *
     * @param params
     */
    @RequestMapping(value = "/editOutputData")
    @ResponseBody
    public void editOutputData(@RequestBody JSONObject params) throws IOException {
        Integer topic_id = params.getInteger("topicId");
        Integer point_id = params.getInteger("pointId");
        String out_data = params.getString("content");
        dao.updateOutDataById(topic_id, point_id, out_data);
//        DataWriteFile(topic_id, point_id, out_data, "out");
    }

}
