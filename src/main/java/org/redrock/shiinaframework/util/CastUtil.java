package org.redrock.shiinaframework.util;

import com.sun.istack.internal.NotNull;

public class CastUtil {
    public static int castInt(@NotNull String text) {
        return Integer.parseInt(text);
    }

    public static int castInt(@NotNull String text,@NotNull int define) {
        int res;
        try {
            res = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            res = define;
        }
        return res;
    }

    public static long castLong(@NotNull String text){
        return Long.parseLong(text);
    }

    public static long castLong(String text,long define) {
        long result;
        try {
            result = Long.parseLong(text);
        } catch (NumberFormatException e) {
            result = define;
        }
        return result;
    }
}
