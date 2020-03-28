package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.Student;
import com.valcano.vjdol.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;

public class StudentDao implements StudentMapper {
    /**
     * 插入考生
     *
     * @param name
     * @param id
     */
    @Override
    public void insertStudent(String name, String id) {
        Student student = new Student();
        Date date = new Date();
        String stu_pwd = String.valueOf(date.getTime()).substring(2, 10) + id.substring(2, 9);
        String stu_no = String.valueOf(date.getTime()).substring(3, 9) + id.substring(5, 9);
        student.setStu_no(stu_no);
        student.setStu_name(name);
        student.setStu_id(id);
        student.setStu_pwd(stu_pwd);
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.insert("insertStudent", student);

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
     * 通过id查询学生
     *
     * @param stuNo
     * @return
     */
    @Override
    public Student queryStudentByid(String stuNo) {
        Student student = new Student();
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            student = session.selectOne("queryStudentByid", stuNo);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return student;
    }


}
