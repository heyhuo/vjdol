package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.Student;

public interface StudentMapper {
    /**
     * 插入学生
     *
     * @param name
     * @param id
     */
    void insertStudent(String name, String id);


    /**
     * 查询学生
     *
     * @param stuNo
     * @return
     */
    Student queryStudentByid(String stuNo);
}
