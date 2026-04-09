package com.hundredz.service;

import com.hundredz.pojo.ClazzQueryParam;
import com.hundredz.pojo.Clazz;
import com.hundredz.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz selectById(Integer id);

    void update(Clazz clazz);

    List<Clazz> findAll();
}
