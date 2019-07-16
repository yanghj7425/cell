package com.self.sell.modules.buyer.controller;

import com.self.sell.common.pojo.vo.ResultVo;
import com.self.sell.common.util.ResultVoUtils;
import com.self.sell.modules.product.entity.ProductCategory;
import com.self.sell.modules.product.entity.ProductInfo;
import com.self.sell.modules.product.pojo.vo.ProductInfoVo;
import com.self.sell.modules.product.pojo.vo.ProductVo;
import com.self.sell.modules.product.service.ProductCategoryService;
import com.self.sell.modules.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("buyer/product")
public class BuyerProductController {


    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("list")
    @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId > 0", unless = "#result.getCode() != 0")
    public ResultVo list(long sellerId) {

        List<ProductInfo> productInfoList = productInfoService.queryAllOnSaleProducts();

        // 2.查询类目
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.queryListByCategoryTypes(categoryTypeList);

        // 3. 拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVos = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVos);
            productVoList.add(productVo);
        }

        // 4.返回
        return ResultVoUtils.success(productVoList);
    }


}
