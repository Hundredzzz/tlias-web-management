package com.hundredz.mapper;

import com.hundredz.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
/**
 * 查询所有部门信息的方法
 * 该方法使用SQL查询语句从数据库中获取所有部门信息，并按照更新时间降序排列

 *
 * @return 返回包含所有部门信息的List集合，集合中每个元素是一个Dept对象
 */

    @Select("select id, name, create_time, update_time from dept order by  update_time desc;") // 使用@Select注解定义SQL查询语句，查询部门表的id、名称、创建时间和更新时间字段，并按更新时间降序排列
    List<Dept> findAll(); // 方法定义，返回类型为List<Dept>，表示包含所有部门信息的列表

    @Select("select count(*) from emp where dept_id=#{id}")
    Integer countEmpByDeptId(Integer id);

    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept selectById(Integer id);

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
