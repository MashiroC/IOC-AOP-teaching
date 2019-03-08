package org.redrock.shiinaframework.core;

import org.redrock.shiinaframework.annotation.Component;
import org.redrock.shiinaframework.util.CastUtil;
import org.redrock.shiinaframework.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Shiina18
 * @date: 2019/3/5 20:36
 * @description:
 */
public class BeenFactory {

    private static BeenFactory beenFactory = null;

    private ClassLoader classLoader = null;
    private Map<Class<?>, Object> componentMap;

    public static BeenFactory getInstance() {
        if (beenFactory == null) {
            synchronized (BeenFactory.class) {
                if (beenFactory == null) {
                    beenFactory = new BeenFactory();
                }
            }
        }
        return beenFactory;
    }

    private BeenFactory() {
        componentMap = new HashMap<>();
        classLoader = ClassLoader.getInstance();
        init();
    }

    private void init() {
        Set<Class<?>> classSet = classLoader.getClassSet();

        for (Class<?> clazz : classSet) {
            Annotation componentAnnotation = clazz.getAnnotation(Component.class);
            if (componentAnnotation != null) {
                Object target = ReflectUtil.newInstance(clazz);
                componentMap.put(clazz, target);
            }
        }
    }

    public <T> T getBeen(Class<T> clazz) {
        T t = (T) componentMap.get(clazz);
        if (t != null) {
            return t;
        }
        throw new RuntimeException("been not found");
    }

}
