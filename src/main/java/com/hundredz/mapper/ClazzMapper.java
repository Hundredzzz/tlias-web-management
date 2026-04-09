package com.hundredz.mapper;

import com.hundredz.pojo.ClazzQueryParam;
import com.hundredz.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface ClazzMapper {


    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz selectById(Integer id);

    void update(Clazz clazz);

    List<Clazz> findAll();

    Integer countStudentByClazzId(Integer id);
}
