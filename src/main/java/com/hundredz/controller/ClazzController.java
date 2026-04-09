package com.hundredz.controller;

import com.hundredz.anno.Log;
import com.hundredz.pojo.ClazzQueryParam;
import com.hundredz.pojo.Clazz;
import com.hundredz.pojo.PageResult;
import com.hundredz.pojo.Result;
import com.hundredz.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService classService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = classService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级：{}", id);
        classService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级：{}", clazz);
        classService.add(clazz);
        return Result.success();
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询班级" + id);
        Clazz clazz = classService.selectById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}", clazz);
        classService.update(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList= classService.findAll();
        return Result.success(clazzList);
    }
}
