package com.self.sell.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class KeyUtils {

    public static synchronized long genUniqueKey() {
        Integer idx = new Random().nextInt(90000) + 10000;
        return System.currentTimeMillis() + idx;
    }


    public static boolean checkId(long id) {

        log.info("【 key check 】 id = {}", id);
        return id > 29;
    }


}
