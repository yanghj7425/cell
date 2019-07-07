package com.self.cell.common.pojo.vo;


import lombok.Data;

/**
 * http 请求 返回前端对象
 */
@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提是信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

}
