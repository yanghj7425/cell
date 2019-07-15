package com.self.sell.common.exception;

import com.self.sell.common.enums.ResultEnum;
import lombok.Data;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }


}
