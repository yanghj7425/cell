package com.self.cell.product.service.impl;

import com.self.cell.product.entity.ProductCategory;
import com.self.cell.product.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Import(MybatisConfig.class)
public class CategoryServiceITest {

    @Autowired
    private CategoryService categoryService;


    @Test
    public void insertTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("unit test");

        Random random = new Random();

        productCategory.setCategoryId(1);
        productCategory.setCategoryType(random.nextInt());
        categoryService.insertOne(productCategory);

    }


}