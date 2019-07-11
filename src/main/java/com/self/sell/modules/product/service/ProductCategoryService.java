package com.self.sell.modules.product.service;

import com.self.sell.common.service.BaseService;
import com.self.sell.modules.product.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategory> queryGreaterThanId(long id);


    List<ProductCategory> queryListByCategoryTypes(List<Integer> categoryTypeList);
}
