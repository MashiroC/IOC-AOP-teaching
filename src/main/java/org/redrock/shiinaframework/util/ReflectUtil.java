package org.redrock.shiinaframework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Shiina18
 * @date: 2019/3/8 10:42
 * @description: 反射工具类
 */
public class ReflectUtil {

    /**
     * 使用反射获得一个类的对象
     * 该类必须有一个无参构造器
     *
     * @param clazz 类
     * @return 类的对象
     */
    public static Object newInstance(Class<?> clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 使用反射获得一个类的对象
     * 该类必须有一个以一个字符串为参数的构造器
     *
     * @param clazz 类
     * @param value 构造器参数
     * @return 类的对象
     */
    public static Object newInstance(Class<?> clazz, String value) {
        Object object = null;
        try {
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            object = constructor.newInstance(value);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            object = -1;
        }
        return object;
    }

    /**
     * 检验一个对象是否为包装类(Integer Long Double)
     * @param clazz 对象的类
     * @return 是否为包装类
     */
    public static boolean isPrimitive(Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            switch (clazz.getName()) {
                case "java.lang.Integer":
                    return true;
                case "java.lang.Long":
                    return true;
                case "java.lang.Double":
                    return true;
                case "java.lang.Float":
                    return true;
                case "java.lang.Boolean":
                    return true;
                case "java.lang.Character":
                    return true;
                case "java.lang.String":
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * 获得值类型的包装类
     * 如果传参不是值类型 那么直接返回原类型
     *
     * @param clazz 值类型
     * @return 包装类
     */
    public static Class<?> getNormalClass(Class<?> clazz) {
        String name = clazz.getName();
        switch (name) {
            case "int":
                return Integer.class;
            case "long":
                return Long.class;
            case "double":
                return Double.class;
            case "float":
                return Float.class;
            case "char":
                return Character.class;
            case "boolean":
                return Boolean.class;
            default:
                return clazz;
        }
    }

    /**
     * 使用反射设置某对象的参数的值
     * @param field 参数
     * @param obj 对象
     * @param value 值
     */
    public static void setFieldValue(Field field, Object obj, Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
