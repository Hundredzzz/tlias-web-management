package com.hundredz.mapper;

import com.hundredz.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历Mapper接口
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入员工工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> empIds);
}
