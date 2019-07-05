package com.self.cell.product.service.impl;

import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.product.dao.ProductCategoryMapper;
import com.self.cell.product.entity.ProductCategory;
import com.self.cell.product.service.CategoryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;

@Service("categoryService")
public class CategoryServiceI extends AbstractBaseService<ProductCategory, Mapper<ProductCategory>> implements CategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

}
