package com.self.sell.common.pojo.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * http 请求 返回前端对象
 */
@Data
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 884552658952951736L;
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
