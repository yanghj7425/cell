package com.self.cell.product.service;

import com.self.cell.common.service.BaseService;
import com.self.cell.product.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService extends BaseService<ProductInfo> {

    List<ProductInfo> queryAllOnSaleProducts();

}
