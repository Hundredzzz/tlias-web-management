package com.hundredz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1; // 默认第一页
    private Integer pageSize = 10; // 默认每页显示10条数据
    private String name; // 员工姓名
    private Integer gender; // 员工性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 入职日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 离职日期
}
