package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.Teacher;

public interface TeacherMapper {
    /**
     * 添加教师账号
     *
     * @param teacher_no
     * @param teacher_pwd
     */
    void insertTeacher(String teacher_no, String teacher_name, String teacher_pwd);

    /**
     * 查询教师
     *
     * @param teacher_no
     * @return
     */
    Teacher queryTeacher(String teacher_no);
}
