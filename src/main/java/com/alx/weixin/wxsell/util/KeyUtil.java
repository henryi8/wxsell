package com.alx.weixin.wxsell.util;

import java.util.Random;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/3
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
