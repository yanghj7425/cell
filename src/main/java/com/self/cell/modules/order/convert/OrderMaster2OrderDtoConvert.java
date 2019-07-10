package com.self.cell.modules.order.convert;

import com.self.cell.modules.order.entity.OrderMaster;
import com.self.cell.modules.order.pojo.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDtoConvert {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }


    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e))
                .collect(Collectors.toList());
    }

}
