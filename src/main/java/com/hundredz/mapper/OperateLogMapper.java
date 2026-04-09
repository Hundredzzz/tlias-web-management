package com.hundredz.mapper;

<<<<<<< HEAD
import com.hundredz.pojo.LogQueryParam;
import com.hundredz.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
=======
import com.hundredz.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
>>>>>>> befb783f2ea6acee673c5b65508aad7c715ea16b

@Mapper
public interface OperateLogMapper {

<<<<<<< HEAD

=======
>>>>>>> befb783f2ea6acee673c5b65508aad7c715ea16b
    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

<<<<<<< HEAD
    @Select("select * from operate_log ")
    List<OperateLog> list(LogQueryParam logQueryParam);
=======
>>>>>>> befb783f2ea6acee673c5b65508aad7c715ea16b
}
