package com.hundredz.service;


import com.hundredz.pojo.Dept;

import java.util.List;

public interface DeptService {

/**
 * 查询所有部门信息的方法
 *
 * @return 返回包含所有部门信息的List集合，每个元素是一个Dept对象
 */
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);

}
