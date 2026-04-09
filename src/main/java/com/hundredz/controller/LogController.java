package com.hundredz.controller;

import com.hundredz.pojo.*;
import com.hundredz.service.EmpService;
import com.hundredz.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(LogQueryParam logQueryParam) {
        // 打印分页查询的参数信息，用于日志记录
        log.info("分页查询：{}",logQueryParam);
        // 调用服务层的分页查询方法，获取分页结果
        PageResult<OperateLog> pageResult = logService.page(logQueryParam);
        // 返回成功结果，包含分页数据
        return Result.success(pageResult);
    }
}
