package com.hundredz.service.impl;


import com.hundredz.exception.BusinessException;
import com.hundredz.mapper.DeptMapper;
import com.hundredz.pojo.Dept;
import com.hundredz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门服务实现类
 * 实现了部门相关的业务逻辑
 */
@Service
public class DeptServiceImpl implements DeptService {

    /**
     * 自动注入部门数据访问层
     * 用于与数据库进行交互
     */
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询所有部门信息
     * @return 返回部门列表
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据ID删除部门
     * @param id 部门ID
     */
    @Override
    public void deleteById(Integer id) throws BusinessException {
        Integer count = deptMapper.countEmpByDeptId(id);
        if (count != null && count > 0) {
            throw new BusinessException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    /**
     * 添加新部门
     * @param dept 部门信息
     */
    @Override
    public void add(Dept dept) {
        // 1. 补全基础属性
        dept.setCreateTime(LocalDateTime.now()); // 设置创建时间为当前时间
        dept.setUpdateTime(LocalDateTime.now()); // 设置更新时间为当前时间
        // 2. 调用Mapper层的方法
        deptMapper.add(dept); // 执行添加操作
    }

    /**
     * 根据ID查询部门信息
     * @param id 部门ID
     * @return 返回部门信息
     */
    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }

    /**
     * 更新部门信息
     * @param dept 部门信息
     */
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now()); // 更新更新时间为当前时间
        deptMapper.update(dept); // 执行更新操作
    }

}
