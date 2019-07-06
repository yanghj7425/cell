package com.self.cell.product.dao;

import com.self.cell.product.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Resource
    private ProductCategoryMapper productCategoryMapper;


    @Test
    public void queryListByPropertyTest() {

        ProductCategory productCategory = new ProductCategory();
        Example example = new Example(productCategory.getClass());

        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("categoryType", Arrays.asList(new Integer[]{232047429, 688599486}));
        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(example);

        productCategories.stream().map(v -> {
            System.out.println(v.getCreateTime());
            return v;
        }).forEach(System.out::println);

    }


    @Test
    public void deleteTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("unit test");
        productCategoryMapper.delete(productCategory);

    }


    @Test
    public void updateTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId((long) 101);
        productCategory.setCategoryName("说个 鸡毛");
        productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }


    @Test
    public void updateExampleTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("洗洗睡把");

        Example example = new Example(productCategory.getClass());
        example.createCriteria().andIn("categoryId", Arrays.asList(new Integer[]{104, 105, 106, 107}));

        productCategoryMapper.updateByExampleSelective(productCategory, example);

    }


}