package com.hundredz.mapper;

import com.hundredz.pojo.Student;
import com.hundredz.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);

    @MapKey("num")
    List<Map<String, Object>> countStudentDegreeData();

    @MapKey("name")
    List<Map<String, Object>> countStudentClazzData();
}
