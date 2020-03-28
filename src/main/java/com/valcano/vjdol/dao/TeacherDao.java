package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.Teacher;
import com.valcano.vjdol.mapper.TeacherMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class TeacherDao implements TeacherMapper {

    /**
     * 添加教师账号
     *
     * @param teacher_no
     * @param teacher_pwd
     */
    @Override
    public void insertTeacher(String teacher_no, String teacher_name, String teacher_pwd) {
        SqlSession session = null;
        Teacher teacher = new Teacher(teacher_no, teacher_name, teacher_pwd);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            int rows = session.insert("insertTeacher", teacher);

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
     * 查询教师
     *
     * @param teacher_no
     * @return
     */
    public Teacher queryTeacher(String teacher_no) {
        SqlSession session = null;
        Teacher teacher = new Teacher();
        teacher.setTeacher_no(teacher_no);
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            teacher = session.selectOne("queryTeacher", teacher);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return teacher;
    }
}
