package com.hundredz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page = 1; // 默认第一页
    private Integer pageSize = 10; // 默认每页显示10条数据
    private String name; // 班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结课日期
}
