package com.hngy.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 构建 统一 响应结果集
 * Integer status
 * T data
 * String msg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private String msg;
    private T data;
}
