package com.self.sell.modules.seckill.controller;


import com.self.sell.common.pojo.vo.ResultVo;
import com.self.sell.common.util.ResultVoUtils;
import com.self.sell.modules.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seckill")
public class SeckillController {


    @Autowired
    private SecKillService secKillService;


    @GetMapping("query/{productId}")
    public ResultVo query(@PathVariable long productId) {
        String result = secKillService.querySecProduct(productId);
        return ResultVoUtils.success(result);
    }


    @GetMapping("order/{productId}")
    public ResultVo rushBuy(@PathVariable long productId) {
        String result = secKillService.rushBuy(productId);
        return ResultVoUtils.success(result);


    }

}
