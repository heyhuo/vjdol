package com.valcano.vjdol.controller;

import com.alibaba.fastjson.JSONObject;
import com.valcano.vjdol.dao.ExamPaperDao;
import com.valcano.vjdol.dao.StudentDao;
import com.valcano.vjdol.dao.TeacherDao;
import com.valcano.vjdol.dao.TopicAnswerDao;
import com.valcano.vjdol.entity.Student;
import com.valcano.vjdol.entity.Teacher;
import com.valcano.vjdol.mapper.ExamPaperMapper;
import com.valcano.vjdol.mapper.StudentMapper;
import com.valcano.vjdol.mapper.TopicAnswerMapper;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    StudentMapper studentDao = new StudentDao();
    ExamPaperMapper paperDao = new ExamPaperDao();
    TopicAnswerMapper answerDao = new TopicAnswerDao();
    TeacherDao teacherDao = new TeacherDao();

    /**
     * 登录考试
     *
     * @param params
     */
    @RequestMapping(value = "/loginExam")
    @ResponseBody
    public Object loginExam(@RequestBody JSONObject params) {
        String no = params.getString("stu_no");
        String pwd = params.getString("stu_pwd");
        System.out.println(no + "--" + pwd);
        Map<String, Object> map = new HashMap<>();
        Student student = studentDao.queryStudentByid(no);
        Teacher teacher = teacherDao.queryTeacher(no);
        if (student != null) {
            if (student.getStu_pwd().equals(pwd)) {
                map.put("success", true);
                System.out.println("密码正确(￣▽￣)~*");
                map.put("message", "examList.html?stu_no=" + no);
            } else {
                map.put("success", false);
                System.out.println("密码错误_(:з」∠)_");
                map.put("message", "密码错误_(:з」∠)_");
            }
        } else if (teacher != null) {
            if (teacher.getTeacher_pwd().equals(pwd)) {
                map.put("success", true);
                System.out.println("密码正确(￣▽￣)~*");
                map.put("message", "topicList.html?tea_no=" + no);
            } else {
                map.put("success", false);
                System.out.println("密码错误_(:з」∠)_");
                map.put("message", "密码错误_(:з」∠)_");
            }
        } else {
            map.put("success", false);
            System.out.println("账号错误_(:з」∠)_");
            map.put("message", "账号错误_(:з」∠)_");
        }
        return map;
    }


}
