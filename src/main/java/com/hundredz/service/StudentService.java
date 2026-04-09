package com.hundredz.service;

import com.hundredz.pojo.PageResult;
import com.hundredz.pojo.Student;
import com.hundredz.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
}
