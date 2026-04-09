package com.hundredz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * 用于存储分页查询后的总记录数和当前页的数据列表
 *
 * @param <T> 泛型类型，表示数据列表中的元素类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    // 总记录数
    private  Long total;
    // 当前页的数据列表
    private List<T> rows;
}
