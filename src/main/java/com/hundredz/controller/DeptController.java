package com.hundredz.controller;

import com.hundredz.anno.Log;
import com.hundredz.pojo.Dept;
import com.hundredz.pojo.Result;
import com.hundredz.service.DeptService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门控制器类
 * 使用@RestController注解标记这是一个RESTful风格的控制器
 * 处理与部门相关的HTTP请求
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //注入部门服务类
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("查询全部部门列表");
        log.info("查询全部部门列表");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(Integer id){
        //System.out.println("删除部门" + id);
        log.info("删除部门：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("添加部门" + dept);
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        //System.out.println("查询部门" + id);
        log.info("查询部门：{}", id);
        Dept dept = deptService.selectById(id);
        return Result.success(dept);

    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
