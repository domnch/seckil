package com.dayup.seckil.util;

import java.util.UUID;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/24 17:08
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
