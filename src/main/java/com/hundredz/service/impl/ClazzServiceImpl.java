package com.hundredz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hundredz.exception.ClazzDeleteException;
import com.hundredz.mapper.ClazzMapper;
import com.hundredz.pojo.ClazzQueryParam;
import com.hundredz.pojo.Clazz;
import com.hundredz.pojo.PageResult;
import com.hundredz.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper classMapper;


    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 2. 执行查询
        List<Clazz> clazzList = classMapper.list(clazzQueryParam);

        // 判断开课状态
        LocalDate now = LocalDate.now();
        clazzList.forEach(clazz -> {
            if (clazz.getEndDate() != null && now.isAfter(clazz.getEndDate())) {
                // 当前时间 > 结课时间：已结课
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate() != null && now.isBefore(clazz.getBeginDate())) {
                // 当前时间 < 开课时间：未开班
                clazz.setStatus("未开班");
            } else {
                // 否则：在读中
                clazz.setStatus("在读中");
            }
        });


        // 3. 封装PageResult对象，并返回
        Page<Clazz> clazzPage = (Page<Clazz>) clazzList;

        return new PageResult<>(clazzPage.getTotal(), clazzList);

    }

    @Override
    public void deleteById(Integer id) {
        Integer count = classMapper.countStudentByClazzId(id);
        if (count != null && count > 0) {
            throw new ClazzDeleteException("对不起，该班级下有学生，不能直接删除");
        }
        classMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        // 1. 补全属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 2. 调用Mapper层方法
        classMapper.add(clazz);

    }

    @Override
    public Clazz selectById(Integer id) {
        return classMapper.selectById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return classMapper.findAll();
    }
}
