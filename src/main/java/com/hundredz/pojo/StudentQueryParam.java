package com.hundredz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentQueryParam {
    private Integer page = 1; // 默认第一页
    private Integer pageSize = 10; // 默认每页显示10条数据
    private String name; // 学生姓名
    private Integer degree; // 学历
    private Integer clazzId; // 班级id
}
