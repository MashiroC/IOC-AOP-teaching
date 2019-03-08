package org.redrock.shiinaframework.util;

import com.sun.istack.internal.NotNull;

/**
 * @author: Shiina18
 * @date: 2019/3/8 10:41
 * @description: 类型转换工具类
 */
public class CastUtil {
    /**
     * 将字符串转换为int值
     * @param text 要转换的字符串
     * @return 转换完的int值，字符串不合法的情况下为0
     */
    public static int castInt(@NotNull String text) {
        return castInt(text, 0);
    }

    /**
     * 将字符串转换为int值，如果字符串不合法，返回一个预设值
     * @param text 要转换的字符串
     * @param define 预设值
     * @return 转换完的int值
     */
    public static int castInt(@NotNull String text, @NotNull int define) {
        int res;
        try {
            res = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            res = define;
        }
        return res;
    }

    /**
     * 将字符串转换为long值
     * @param text 要转换的字符串
     * @return 转换完的long值，字符串不合法的情况下为0
     */
    public static long castLong(@NotNull String text) {
        return castLong(text, 0L);
    }

    /**
     * 将字符串转换为long值，如果字符串不合法，返回一个预设值
     * @param text 要转换的字符串
     * @param define 预设值
     * @return 转换完的long值
     */
    public static long castLong(String text, long define) {
        long result;
        try {
            result = Long.parseLong(text);
        } catch (NumberFormatException e) {
            result = define;
        }
        return result;
    }
}
