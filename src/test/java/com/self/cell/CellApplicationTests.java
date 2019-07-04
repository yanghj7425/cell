package com.self.cell;

import com.self.cell.product.dao.ProductCategoryMapper;
import com.self.cell.product.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CellApplicationTests {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void contextLoads() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("unit test");

        Random random = new Random();

        productCategory.setCategoryId(1);
        productCategory.setCategoryType(random.nextInt());
        productCategoryMapper.updateByPrimaryKey(productCategory);
    }

}
