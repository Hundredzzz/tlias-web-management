package com.hundredz.service;

import com.hundredz.pojo.Emp;
import com.hundredz.pojo.LogQueryParam;
import com.hundredz.pojo.OperateLog;
import com.hundredz.pojo.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
    PageResult<OperateLog> page(LogQueryParam logQueryParam);

}
