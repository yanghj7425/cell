package com.self.cell.product.service.impl;

import com.self.cell.common.enums.ResultEnum;
import com.self.cell.common.exception.CellException;
import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.product.dao.ProductInfoMapper;
import com.self.cell.product.enums.ProductStatusEnum;
import com.self.cell.product.entity.ProductInfo;
import com.self.cell.product.pojo.dto.CartDto;
import com.self.cell.product.service.ProductInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
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

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = queryOneById(cartDto.getProductId());
            if (productInfo == null) {
                throw new CellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int stock = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(stock);
            updateSelectiveById(productInfo);
        }
    }


    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = queryOneById(cartDto.getProductId());
            if (productInfo == null) {
                throw new CellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() - cartDto.getProductQuantity();

            if (result < 0) {
                throw new CellException(ResultEnum.STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            updateSelectiveById(productInfo);
        }
    }
}
