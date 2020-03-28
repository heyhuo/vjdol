package com.valcano.vjdol.controller;

import com.alibaba.fastjson.JSONObject;
import com.valcano.vjdol.dao.TestPointDao;
import com.valcano.vjdol.dao.TopicAnswerDao;
import com.valcano.vjdol.dao.TopicDao;
import com.valcano.vjdol.entity.TestPoint;
import com.valcano.vjdol.entity.Topic;
import com.valcano.vjdol.entity.TopicAnswer;
import com.valcano.vjdol.mapper.TestPointMapper;
import com.valcano.vjdol.mapper.TopicMapper;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {
    TopicMapper dao = new TopicDao();
    TestPointMapper testPointDao = new TestPointDao();
    TopicAnswerDao answerDao = new TopicAnswerDao();

    /**
     * 更新标题
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateTitle")
    @ResponseBody
    public Object updateTitle(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        String title = params.getString("topicContent");
        //通过id更新标题
        dao.updateTopicTitle(title, id);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "编辑标题成功了！");
        return map;
    }

    /**
     * 更新内容
     *
     * @param params
     * @return
     */

    @RequestMapping(value = "/updateContent")
    @ResponseBody
    public Object updateContent(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        String content = params.getString("topicContent");
        //通过id更新标题
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "编辑内容成功了！");
        dao.updateTopicContent(content, id);
        return map;
    }

    /**
     * 更新输入格式
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateInputFormat")
    @ResponseBody
    public Object updateInputFormat(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        String inputFormat = params.getString("topicContent");
        dao.updateTopicInputFormat(inputFormat, id);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "编辑输入格式成功了！");

        return map;
    }

    /**
     * 更新输出格式
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateOutputFormat")
    @ResponseBody
    public Object updateOutputFormat(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        String outputFormat = params.getString("topicContent");
        //通过id更新标题
        dao.updateTopicOutputFormat(outputFormat, id);
        System.out.println("id->" + id + " name—>" + outputFormat);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "编辑输出格式成功了！");
        return map;
    }

    /**
     * 更新输入样例
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateInputSample")
    @ResponseBody
    public Object updateInputSample(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        String inputSample = params.getString("topicContent");

        dao.updateTopicInputSample(inputSample, id);

        System.out.println("id->" + id + " name—>" + inputSample);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "编辑输入格式成功了！");

        return map;
    }

    /**
     * 更新输出样例
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateOutputSample")
    @ResponseBody
    public Object updateOutputSample(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        String outputFormat = params.getString("topicContent");

        dao.updateTopicOutputSample(outputFormat, id);
        System.out.println("id->" + id + " name—>" + outputFormat);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "编辑输出样例成功了！");
        return map;
    }

    /**
     * 查询所有题目
     *
     * @return
     */
    @RequestMapping(value = "/queryAllTopic")
    @ResponseBody
    public List<Topic> quertAllTopic() {
        return dao.queryAllTopic();
    }

    /**
     * 查询已发布的题目
     *
     * @return
     */
    @RequestMapping(value = "/queryPublishTopic")
    @ResponseBody
    public List<Topic> queryPublishTopic() {
        return dao.queryPublishTopic();
    }

    /**
     * 删除题目
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/deleteTopicById")
    @ResponseBody
    public void deleteTopicById(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        //通过id更新标题
        TestPointMapper testPointMapper = new TestPointDao();
        testPointMapper.deleteTestPointAll(id);
        dao.deleteTopicById(id);
    }

    /**
     * 添加题目
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/createTopic")
    @ResponseBody
    public Object createTopic(@RequestBody JSONObject params) {
        Integer id = params.getInteger("id");
        int flag = dao.quryTopicById(id).getTopic_id();
        Map<String, Object> map = new HashMap<>();
        if (flag == -1) {
            map.put("flag", true);
            dao.insertTopic(id);
        } else {
            map.put("flag", false);
            map.put("message", "此编号题目已存在！");
        }
        return map;
    }

    /**
     * 返回题目内容
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/editTopic")
    @ResponseBody
    public Topic editTopic(@RequestBody JSONObject params) {
        Topic topic = new Topic();
        Integer id = params.getInteger("id");
        topic = dao.quryTopicById(id);
        if (topic.getTopic_id() != -1) {
            //1.标题
            if (topic.getTopic_title() == null)
                topic.setTopic_title("在这里预览你所编辑的标题");
            //2.内容
            if (topic.getTopic_content() == null)
                topic.setTopic_content("在这里预览你所编辑的内容");
            //3.输入格式
            if (topic.getInput_format() == null)
                topic.setInput_format("在这里预览你所编辑的输入格式");
            //4.输出格式
            if (topic.getOutput_format() == null)
                topic.setOutput_format("在这里预览你所编辑的输出格式");
            //5.输入样例
            if (topic.getInput_sample() == null)
                topic.setInput_sample("在这里预览你所编辑的输入样例");
            //6.输出样例
            if (topic.getOutput_sample() == null)
                topic.setOutput_sample("在这里预览你所编辑的输出样例");
        }
        return topic;
    }


    /**
     * 查询所有答案
     *
     * @return
     */
    @RequestMapping(value = "/queryTopicAnswer")
    @ResponseBody
    public List<TopicAnswer> queryTopicAnswer() {

        return answerDao.queryAllAnswer();
    }

    /**
     * 查询分数
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryScore")
    @ResponseBody
    public Topic queryScore(@RequestBody JSONObject params) {
        int topicId = params.getInteger("topicId");
        return dao.quryTopicById(topicId);
    }


    /**
     * 通过学号题号查询答案
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryAnswer")
    @ResponseBody
    public TopicAnswer queryAnswerByStuNoTopicId(@RequestBody JSONObject params) {
        String stu_no = params.getString("stuNo");
        int topicId = params.getInteger("topicId");
        TopicAnswer answer = new TopicAnswer();
        return answerDao.queryAnswerByStuNoTopicId(stu_no, topicId);
    }

    /**
     * 编辑题目发布状态
     *
     * @param params
     */
    @RequestMapping(value = "/editState")
    @ResponseBody
    public void editState(@RequestBody JSONObject params) {
        String state = params.getString("state").toString();
        Integer id = params.getInteger("topicId");
        dao.editState(id, state);
    }

    /**
     * 更新运行时限
     *
     * @param params
     */
    @RequestMapping(value = "/updateTimeLimitById")
    @ResponseBody
    public void updateTimeLimitById(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        Integer time_limit = params.getInteger("content");
        dao.updateTopicTimeLimitById(id, time_limit);
    }

    /**
     * 更新内存限制
     *
     * @param params
     */
    @RequestMapping(value = "/updateMemoryLimitById")
    @ResponseBody
    public void updateMemoryLimitById(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        Integer memory_limit = params.getInteger("content");
        dao.updateTopicMemoryLimitById(id, memory_limit);
    }


    /**
     * 更新题目分数
     *
     * @param params
     */
    @RequestMapping(value = "/updateTopicScoreById")
    @ResponseBody
    public void updateTopicScoreById(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        Integer topic_score = params.getInteger("content");
        dao.updateTopicScoreById(id, topic_score);
    }

    /**
     * 更新学生题目得分
     */

    @RequestMapping(value = "/updateTopicScore")
    @ResponseBody
    public void updateTopicScore(@RequestBody JSONObject params) {
        Integer topic_id = params.getInteger("topicId");
        String stu_no = params.getString("stuNo");
        Integer topic_score = params.getInteger("topicScore");
        answerDao.updateTopicScore(stu_no, topic_score, topic_score);
    }

    /**
     * 查询是否符合发布要求
     *
     * @param params
     */
    @RequestMapping(value = "/queryState")
    @ResponseBody
    public Object queryState(@RequestBody JSONObject params) {
        Integer id = params.getInteger("topicId");
        Topic topic = dao.quryTopicById(id);
        List<TestPoint> testPointList = testPointDao.queryPoint(id);
        Map<String, Object> map = new HashMap<>();
        if (topic.getTopic_title() == null &&
                topic.getTopic_content() == null &&
                topic.getInput_format() == null &&
                topic.getOutput_format() == null &&
                topic.getInput_sample() == null &&
                topic.getOutput_sample() == null &&
                topic.getTime_limit() <= 0 &&
                topic.getMemory_limit() <= 0 &&
                topic.getTopic_score() <= 0 &&
                topic.getPoint_num() <= 0) {
            System.out.println(topic.toString());
            map.put("success", false);
            map.put("message", "题目信息不完整，还不能发布￣ω￣=！");
        } else {
            boolean pointFlag = true;
            for (int i = 0; i < testPointList.size(); i++) {
                if (testPointList.get(i).getInput_data() == null &&
                        testPointList.get(i).getInput_data() == null &&
                        testPointList.get(i).getOut_data() == null) {
                    pointFlag = false;
                }
            }
            if (testPointList.size() > 0 && pointFlag) {
                map.put("success", true);
                map.put("message", "题目发布成功(￣▽￣)~*");
            } else {
                map.put("success", false);
                map.put("message", "测试点信息不完整，还不能发布￣ω￣=！");
            }

        }
        return map;
    }
}
