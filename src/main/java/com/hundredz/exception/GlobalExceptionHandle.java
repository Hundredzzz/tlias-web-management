package com.hundredz.exception;

import com.hundredz.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Arrays;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.error("系统异常，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("系统异常：", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }

    @ExceptionHandler
    public Result handleBusinessException(BusinessException e) {
        log.error("业务异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result handleClazzDeleteException(ClazzDeleteException e) {
        log.error("班级删除异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
