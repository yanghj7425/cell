package com.self.cell.common.util;

import com.self.cell.common.pojo.vo.ResultVo;

public class ResultVoUtils {

    public static ResultVo success(Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        resultVo.setCode(1);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();

        resultVo.setMsg(msg);
        resultVo.setCode(code);

        return resultVo;
    }


}
