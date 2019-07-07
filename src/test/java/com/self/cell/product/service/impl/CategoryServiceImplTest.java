package com.self.cell.product.service.impl;

import com.self.cell.product.entity.ProductCategory;
import com.self.cell.product.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void insertOne() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("unit test");
        Random random = new Random();
        productCategory.setCategoryId((long) 1);
        productCategory.setCategoryType(random.nextInt());
        productCategoryService.insertOne(productCategory);

    }

    @Test
    public void insertList() {
        List<ProductCategory> categoryList = new ArrayList<>();
        int start = 500;
        int size = 5;
        for (int i = start; i < start + size; i++) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName("类别名称" + i + "号");
            productCategory.setCategoryId((long) i);
            productCategory.setCategoryType(i % size);
            categoryList.add(productCategory);
        }
        Integer integer = productCategoryService.insertList(categoryList);

        Assert.assertEquals(new Integer(size), integer);

        List<ProductCategory> productCategories = productCategoryService.queryGreaterThanId(500);
        Assert.assertNotEquals(0, productCategories.size());

        productCategories.forEach(e -> log.debug(e.toString()));


    }

    @Test
    public void deleteOneById() {
        productCategoryService.deleteOneById((long) 1);
    }

    @Test
    public void deleteByWhere() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("unit test");
        productCategoryService.deleteByWhere(productCategory);

    }

    @Test
    public void updateSelectiveById() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId((long) 102);
        productCategory.setCategoryName("别瞎 JB 乱搞");
        productCategoryService.updateSelectiveById(productCategory);

    }

    @Test
    @Transactional
    public void updateSelectiveByProperty() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("碧海无垠");
        productCategoryService.updateSelectiveByProperty(productCategory, "categoryId", Arrays.asList(new Integer[]{111, 112, 113, 114}));

        List<ProductCategory> productCategories = productCategoryService.queryListByProperty(ProductCategory.class, "categoryName", Arrays.asList(new String[]{"碧海无垠"}));
        Assert.assertNotEquals(0, productCategories.size());
        productCategories.forEach(e -> log.debug(e.getCategoryName()));

    }


    @Test
    public void updateSelectiveByPropertyTest2() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("秋水无波");
        productCategoryService.updateSelectiveByProperty(productCategory, "categoryName", Arrays.asList(new String[]{"碧海无垠"}));


    }

    @Test
    public void queryOneById() {
        ProductCategory productCategory = productCategoryService.queryOneById((long) 0);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void queryListByProperty() {
        List<ProductCategory> productCategories = productCategoryService.queryListByProperty(ProductCategory.class, "categoryName", Arrays.asList(new String[]{"unit test"}));
        Assert.assertNotEquals(0, productCategories.size());

        productCategories.forEach(v -> log.debug(v.toString()));
    }

    @Test
    public void queryGreaterThanId() {
        List<ProductCategory> productCategories = productCategoryService.queryGreaterThanId(500);
        Assert.assertNotEquals(0, productCategories.size());
        productCategories.forEach(e -> log.debug(e.toString()));

    }

    @Test
    public void queryAll() {
        List<ProductCategory> productCategories = productCategoryService.queryAll();
        Assert.assertNotEquals(0, productCategories.size());
        productCategories.stream().forEach(System.out::println);
    }
}