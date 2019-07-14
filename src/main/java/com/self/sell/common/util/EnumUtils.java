package com.self.sell.common.util;

import com.self.sell.common.enums.CodeEnum;

public class EnumUtils {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

}
