package com.hundredz.controller;


import com.hundredz.pojo.PageResult;
import com.hundredz.pojo.Result;
import com.hundredz.pojo.Student;
import com.hundredz.pojo.StudentQueryParam;
import com.hundredz.service.StudentService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询： {}",studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 批量删除学员
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学员： {}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 添加学员
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学员： {}",student);
        studentService.add(student);
        return Result.success();
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询： {}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改员工信息： {}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id , @PathVariable Integer score){
        log.info("违纪处理： {},{}",id,score);
        studentService.violation(id,score);
        return Result.success();

    }

}
