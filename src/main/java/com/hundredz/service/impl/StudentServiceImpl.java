package com.hundredz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hundredz.mapper.StudentMapper;
import com.hundredz.pojo.PageResult;
import com.hundredz.pojo.Student;
import com.hundredz.pojo.StudentQueryParam;
import com.hundredz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        // 2. 执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);

        // 3. 封装分页结果
        Page<Student> studentPage = (Page<Student>) studentList;

        return new PageResult<>(studentPage.getTotal(), studentList);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);

    }
}
