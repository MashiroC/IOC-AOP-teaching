package org.redrock.shiinaframework.util;

/**
 * @author: Shiina18
 * @date: 2019/3/6 17:30
 * @description: 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param text 字符串
     * @return 是否
     */
    public static boolean isEmpty(String text) {
        return text == null || "".equals(text);
    }

    /**
     * 将字符串数组连接为字符串
     * @param array 字符串数组
     * @return 字符串
     */
    public static String append(String... array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
         sb.append(str);
        }
        return sb.toString();
    }
}
