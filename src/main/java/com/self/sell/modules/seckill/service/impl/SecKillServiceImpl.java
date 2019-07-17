package com.self.sell.modules.seckill.service.impl;

import cn.hutool.core.map.MapUtil;
import com.self.sell.common.component.RedisUtils;
import com.self.sell.common.exception.SellException;
import com.self.sell.common.util.KeyUtils;
import com.self.sell.modules.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    private RedisUtils redisUtils;

    private static final int TIMEOUT = 1000;


    // 库存
    private static Map<Long, Integer> stock = MapUtil.newHashMap();

    // 商品
    private static Map<Long, Integer> products = MapUtil.newHashMap();

    // 订单
    private static Map<Long, Long> orders = MapUtil.newHashMap();


    static {
        stock.put(1L, 10_000_000);
        products.put(1L, 10_000_000);
    }


    @Override

    public String querySecProduct(long productId) {
        return "商品 限量" + products.get(productId) + " 份 , " +
                "剩余 " + stock.get(productId) + ";" +
                " 已有 " + orders.size() + "下单成功";
    }

    @Override
    public String rushBuy(long productId) {
        String time = String.valueOf(System.currentTimeMillis() + TIMEOUT);

        boolean lock = redisUtils.lock(String.valueOf(productId), time);
        if (!lock) {
            throw new SellException(888, "活动结束");
        }

        Integer stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(999, "活动结束");
        }
        orders.put(KeyUtils.genUniqueKey(), productId);
        stockNum = stockNum - 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.interrupted();
            e.printStackTrace();
        }
        stock.put(productId, stockNum);

        redisUtils.unlock(String.valueOf(productId), time);


        return "ok";

    }
}
