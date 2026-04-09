package com.hundredz.service;

import com.hundredz.pojo.JobOption;
import com.hundredz.pojo.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    StudentOption getStudentCountData();
}
