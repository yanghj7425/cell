package com.self.cell.modules.product.service;

import com.self.cell.common.service.BaseService;
import com.self.cell.modules.product.pojo.dto.CartDto;
import com.self.cell.modules.product.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService extends BaseService<ProductInfo> {

    List<ProductInfo> queryAllOnSaleProducts();

    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);
}
