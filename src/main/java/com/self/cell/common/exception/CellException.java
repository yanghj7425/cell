package com.self.cell.common.exception;

import com.self.cell.common.enums.ResultEnum;

public class CellException extends RuntimeException {

    private Integer code;

    public CellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public CellException(Integer code, String message) {
        super(message);
        this.code = code;
    }


}
