package com.self.cell.product.service.impl;

import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.product.dao.ProductInfoMapper;
import com.self.cell.product.enums.ProductStatusEnum;
import com.self.cell.product.entity.ProductInfo;
import com.self.cell.product.service.ProductInfoService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductInfoServiceImpl extends AbstractBaseService<ProductInfo, Mapper<ProductInfo>> implements ProductInfoService {

    @Resource
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> queryAllOnSaleProducts() {
        Example example = new Example(ProductInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productStatus", ProductStatusEnum.UP.getCode());
        return queryListByExample(example);
    }
}
