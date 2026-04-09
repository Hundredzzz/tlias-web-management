package com.hundredz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hundredz.mapper.OperateLogMapper;
import com.hundredz.pojo.Emp;
import com.hundredz.pojo.LogQueryParam;
import com.hundredz.pojo.OperateLog;
import com.hundredz.pojo.PageResult;
import com.hundredz.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> page(LogQueryParam logQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());

        // 2. 执行查询
        List<OperateLog> operateLogList = operateLogMapper.list(logQueryParam);

        // 3. 封装PageResult对象，并返回
        Page<OperateLog> operateLogPage = (Page<OperateLog>) operateLogList;

        return new PageResult<>(operateLogPage.getTotal(), operateLogList);
    }
}
