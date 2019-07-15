package com.self.sell.modules.handler;

import com.self.sell.common.exception.SellException;
import com.self.sell.common.pojo.vo.ResultVo;
import com.self.sell.common.util.ResultVoUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVo handlerSellerException(SellException e) {
        return ResultVoUtils.error(e.getCode(), e.getMessage());
    }

}
