package com.self.sell.modules.product.service;

import com.self.sell.common.service.BaseService;
import com.self.sell.modules.product.entity.ProductInfo;
import com.self.sell.modules.product.pojo.dto.CartDto;

import java.util.List;

public interface ProductInfoService extends BaseService<ProductInfo> {

    List<ProductInfo> queryAllOnSaleProducts();

    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);
}
