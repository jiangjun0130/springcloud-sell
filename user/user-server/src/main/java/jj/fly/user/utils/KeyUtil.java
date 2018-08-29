package jj.fly.user.utils;

import java.util.Random;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:53
 * Description:
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
