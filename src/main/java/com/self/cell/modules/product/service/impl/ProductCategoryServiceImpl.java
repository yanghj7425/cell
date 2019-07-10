package com.self.cell.modules.product.service.impl;

import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.modules.product.service.ProductCategoryService;
import com.self.cell.modules.product.dao.ProductCategoryMapper;
import com.self.cell.modules.product.entity.ProductCategory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCategoryServiceImpl extends AbstractBaseService<ProductCategory, Mapper<ProductCategory>> implements ProductCategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;


    @Override
    public List<ProductCategory> queryGreaterThanId(long id) {
        Example example = createExample();
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("categoryId", 500);
        return queryListByExample(example);
    }


    @Override
    public List<ProductCategory> queryListByCategoryTypes(List<Integer> categoryTypeList) {
        Example example = createExample();
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("categoryType", categoryTypeList);

        return queryListByExample(example);
    }

    private Example createExample() {
        return new Example(ProductCategory.class);
    }
}

