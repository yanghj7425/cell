package com.self.cell.product.service.impl;

import com.self.cell.product.entity.ProductCategory;
import com.self.cell.product.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceITest {

    @Autowired
    private CategoryService categoryService;


    public void insertOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("unit test");
        Random random = new Random();
        productCategory.setCategoryId((long) 1);
        productCategory.setCategoryType(random.nextInt());
        categoryService.insertOne(productCategory);
    }

    @Test
    public void insertListTest() {
        List<ProductCategory> categoryList = new ArrayList<>();
        int start = 100;
        Random random = new Random();
        for (int i = start; i < start + 100; i++) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName("unit test");
            productCategory.setCategoryId((long) i);
            productCategory.setCategoryType(random.nextInt());
            categoryList.add(productCategory);
        }
        Integer integer = categoryService.insertList(categoryList);
        System.out.println(integer);
    }


    public void deleteOneByIdTest() {
        categoryService.deleteOneById((long) 1);
    }


    @Test
    public void deleteByWhereTest() {

        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("unit test");

        categoryService.deleteByWhere(productCategory);

    }


    @Test
    public void updateSelectiveByIdTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId((long) 102);
        productCategory.setCategoryName("别瞎 JB 乱搞");
        categoryService.updateSelectiveById(productCategory);
    }


    @Test
    public void updateSelectiveByPropertyTest1() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("碧海无垠");
        categoryService.updateSelectiveByProperty(productCategory, "categoryId", Arrays.asList(new Integer[]{111, 112, 113, 114}));
    }


    @Test
    public void updateSelectiveByPropertyTest2() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("秋水无波");
        categoryService.updateSelectiveByProperty(productCategory, "categoryName", Arrays.asList(new String[]{"碧海无垠"}));
    }


    public void queryOneById() {

        ProductCategory productCategory = categoryService.queryOneById((long) 1);
        System.out.println(productCategory);

    }


    @Test
    public void queryAllTest() {
        List<ProductCategory> productCategories = categoryService.queryAll();
        productCategories.stream().forEach(System.out::println);
    }


}