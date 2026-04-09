package com.hundredz.service.impl;

import com.hundredz.mapper.EmpMapper;
import com.hundredz.mapper.StudentMapper;
import com.hundredz.pojo.JobOption;
import com.hundredz.pojo.StudentOption;
import com.hundredz.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    public ReportServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public JobOption getEmpJobData() {
        // 1. 调用Mapper接口，获取统计数据
        List<Map<String , Object>> list = empMapper.countEmpJobData();

        // 2. 组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();

        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("number")).toList();


        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {

        return studentMapper.countStudentDegreeData();
    }

    @Override
    public StudentOption getStudentCountData() {

        // 1. 调用Mapper接口，获取统计数据
        List<Map<String , Object>> list = studentMapper.countStudentClazzData();

        // 2. 组装结果，并返回
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("number")).toList();

        return new StudentOption(clazzList, dataList);
    }
}
