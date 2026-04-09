package com.hundredz.service;

import com.hundredz.pojo.Emp;
import com.hundredz.pojo.EmpQueryParam;
import com.hundredz.pojo.LoginInfo;
import com.hundredz.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);


/**
 * 批量删除员工信息
 */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */

    Emp getById(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void update(Emp emp);

    List<Emp> list();

    /**
     * 登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
