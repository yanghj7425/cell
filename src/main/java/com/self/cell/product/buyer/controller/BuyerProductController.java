package com.self.cell.product.buyer.controller;

import com.self.cell.common.pojo.vo.ResultVo;
import com.self.cell.common.util.ResultVoUtils;
import com.self.cell.product.entity.ProductCategory;
import com.self.cell.product.entity.ProductInfo;
import com.self.cell.product.service.ProductCategoryService;
import com.self.cell.product.service.ProductInfoService;
import com.self.cell.product.pojo.vo.ProductInfoVo;
import com.self.cell.product.pojo.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product/buyer")
public class BuyerProductController {


    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("list")
    public ResultVo list() {


        // 1. 查询所有上架的商品
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
