package com.self.cell.order.pay.service.impl;

import com.self.cell.common.enums.ResultEnum;
import com.self.cell.common.exception.CellException;
import com.self.cell.order.pay.service.OrderPayService;
import com.self.cell.order.pojo.dto.OrderDto;
import com.self.cell.order.service.OrderService;
import javafx.scene.control.Cell;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderPayServiceImpl implements OrderPayService {


    @Autowired
    private OrderService orderService;


    @Override
    public void create(OrderDto orderDto) {
        // TODO: 调用支付平台支付
    }

    /**
     * <pre>异步通知</pre>
     *
     * @param notifyData 通知数据
     * @return void
     */

    @Override
    public void asyncNotify(String notifyData) {
        // 1. 验证签名

        // 2. 验证支付状态

        long orderId = 1L;
        OrderDto orderDto = orderService.queryOne(orderId);
        if (orderDto == null) {
            log.error("【 微信支付 】, 订单不存在 orderId = {}", orderDto);
            throw new CellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 3. 验证支付金额（第 1,2 步中 会拿到 支付金额信息（），和 orderDto 中做比较）

        // 假定支付金额
        BigDecimal payAmount = new BigDecimal(3);


        // 如果金额相减 小于某个精度
        if (orderDto.getBuyerAmount().subtract(payAmount).doubleValue() != 0.001) {
            log.error("【 微信支付 】 支付金额错误, orderId = {},应支付 {}, 实支付 {}", orderId, orderDto.getBuyerAmount(), payAmount);
            throw new CellException(ResultEnum.ORDER_PAY_AMOUNT_ERROR);
        }


        // 4. 验证支付人 (是否只能由本人支付)

        // 修改订单支付状态
        orderService.paid(orderDto);
    }
}
