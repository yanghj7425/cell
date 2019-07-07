package com.self.cell.common.util;

import java.util.Random;

public class KeyUtils {

    public static synchronized long genUniqueKey() {
        Integer idx = new Random().nextInt(90000) + 10000;
        return System.currentTimeMillis() + idx;
    }


}
