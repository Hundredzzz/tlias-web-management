package com.hundredz.controller;

import com.hundredz.pojo.JobOption;
import com.hundredz.pojo.Result;
import com.hundredz.pojo.StudentOption;
import com.hundredz.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption =  reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList  =  reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历人数");
        List<Map<String, Object>> degreeList  =  reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        StudentOption studentOption =   reportService.getStudentCountData();
        return Result.success(studentOption);
    }
}
