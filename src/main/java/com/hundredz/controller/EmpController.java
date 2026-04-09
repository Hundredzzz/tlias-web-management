package com.hundredz.controller;


import com.hundredz.mapper.EmpMapper;
import com.hundredz.pojo.Emp;
import com.hundredz.pojo.EmpQueryParam;
import com.hundredz.pojo.PageResult;
import com.hundredz.pojo.Result;
import com.hundredz.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 员工控制器类
 * 使用@Slf4j注解自动生成日志对象
 * 使用@RestController注解标记这是一个RESTful控制器，所有方法默认返回JSON格式数据
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     */
    @GetMapping

    public Result page(EmpQueryParam empQueryParam){
    // 打印分页查询的参数信息，用于日志记录
        log.info("分页查询：{}",empQueryParam);
    // 调用服务层的分页查询方法，获取分页结果
        PageResult<Emp> pageResult = empService.page(empQueryParam);
    // 返回成功结果，包含分页数据
        return Result.success(pageResult);
    }


    /**
     * 新增员工
     */
    @PostMapping    // 标记这是一个处理POST请求的方法
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工--数组
     */
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("删除员工：{}", Arrays.toString(ids));
//        return Result.success();
//    }

    /**
     * 删除员工--集合
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工信息：{}",id);
        Emp emp =  empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询全部员工
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部员工");
        List<Emp> emps = empService.list();
        return Result.success(emps);
    }

}
