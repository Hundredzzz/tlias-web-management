package com.hundredz.mapper;

import com.hundredz.pojo.Emp;
import com.hundredz.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工Mapper接口
 */
@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//
//
//    // 分页查询
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工的基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 设置主键回填
    @Insert("insert  into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "VALUES (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工基本信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    List<Emp> listAll();

    Emp selectByUsernameAndPassword(Emp emp);
}
