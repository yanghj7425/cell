package com.self.cell.product.service;

import com.self.cell.common.service.BaseService;
import com.self.cell.product.entity.ProductInfo;
import com.self.cell.product.pojo.dto.CartDto;

import java.util.List;

public interface ProductInfoService extends BaseService<ProductInfo> {

    List<ProductInfo> queryAllOnSaleProducts();

    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);
}
