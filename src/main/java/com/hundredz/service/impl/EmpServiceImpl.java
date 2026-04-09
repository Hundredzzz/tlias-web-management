package com.hundredz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hundredz.mapper.EmpExprMapper;
import com.hundredz.mapper.EmpMapper;
import com.hundredz.pojo.*;
import com.hundredz.service.EmpLogService;
import com.hundredz.service.EmpService;
import com.hundredz.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    // pageHelper
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        // 3. 封装PageResult对象，并返回
        Page<Emp> empPage = (Page<Emp>) empList;

        return new PageResult<>(empPage.getTotal(), empList);
    }

    @Transactional(rollbackFor = Exception.class) // 事务管理注释 -- 默认出现运行时异常才会回滚
    @Override
    public void save(Emp emp) {
        try {
            // 1. 保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 2. 保存员工工作经理信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                // 遍历集合，为empId赋值
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));

                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 记录员工操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 1. 删除员工基本信息
        empMapper.deleteByIds(ids);

        // 2. 删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        // 1. 根据员工id修改基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 2.修改工作经历信息，先删除在添加
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> list() {
        return empMapper.listAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1. 调用mapper接口，根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        // 2. 判断是否存在这个员工，如果存在封装传统登陆成功的信息
        if (e != null) {
            log.info("登录成功：{}", e);
            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(),jwt);

        }

        // 3. 不存在，返回null
        return null;
    }

    //    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // 1. 调用Mapper接口，查询总记录数
//        Long total = empMapper.count();
//
//        // 2. 调用Mapper接口，查询结果
//        Integer start = (page - 1) * pageSize;
//        //List<Emp> rows = empMapper.list(start, pageSize);
//
//        // 3. 封装PageResult对象，并返回
//        return new PageResult<Emp>(total, rows);
//    }
}
