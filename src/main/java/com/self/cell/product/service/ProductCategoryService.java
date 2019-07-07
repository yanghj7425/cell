package com.self.cell.product.service;

import com.self.cell.common.service.BaseService;
import com.self.cell.product.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategory> queryGreaterThanId(long id);


    List<ProductCategory> queryListByCategoryTypes(List<Integer> categoryTypeList);
}
