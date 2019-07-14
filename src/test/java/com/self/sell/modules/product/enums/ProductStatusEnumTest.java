package com.self.sell.modules.product.enums;

import com.self.sell.common.util.EnumUtils;
import org.junit.Test;

public class ProductStatusEnumTest {


    @Test
    public void enumTypeTest() {
        ProductStatusEnum byCode = EnumUtils.getByCode(0, ProductStatusEnum.class);

        System.out.println(byCode.getMessage());

    }

}