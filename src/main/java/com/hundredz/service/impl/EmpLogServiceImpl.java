package com.hundredz.service.impl;

import com.hundredz.mapper.EmpLogMapper;
import com.hundredz.pojo.EmpLog;
import com.hundredz.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 需要在一个新的食物中进行
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
