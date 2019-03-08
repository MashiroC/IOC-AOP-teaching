package org.redrock.shiinaframework.core;

import org.redrock.shiinaframework.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Shiina18
 * @date: 2019/3/5 20:34
 * @description: 类加载器
 */
public class ClassLoader {

    /**
     * 单例
     */
    private static ClassLoader singleton;

    /**
     * 包下面所有类的集合
     */
    private Set<Class<?>> classSet;

    private static final String PROTOCOL_TYPE = "file";

    private static final String PACKAGE_NAME = "packageName";

    private static final String POINT = ".";

    private static final String END_WITH_CLASS = ".class";

    private static final String EMPTY_STRING = "";

    /**
     * 获得单例
     * @return 单例
     */
    public static ClassLoader getInstance() {
        if (singleton == null) {
            synchronized (ClassLoader.class) {
                if (singleton == null) {
                    singleton = new ClassLoader(PropsLoader.getInstance());
                }
            }
        }
        return singleton;
    }

    /**
     * 不允许任何方式调用无参构造器
     * @throws IllegalAccessException 异常
     */
    private ClassLoader() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    private ClassLoader(PropsLoader propsLoader) {
        load(propsLoader);
    }

    /**
     * 包扫描
     * @param propsLoader 配置加载器
     */
    private void load(PropsLoader propsLoader) {
        classSet = new HashSet<>();

        String packageName = propsLoader.getString(PACKAGE_NAME);

        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resources.hasMoreElements()) {
                URL target = resources.nextElement();
                String protocol = target.getProtocol();
                if (protocol.equalsIgnoreCase(PROTOCOL_TYPE)) {
                    String packagePath = target.getPath();
                    loadClass(packageName, packagePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Class<?>> getClassSet() {
        return this.classSet;
    }

    /**
     * 加载类
     * @param packageName 包名
     * @param packagePath 包路径
     */
    private void loadClass(String packageName, String packagePath) {
        File[] files = new File(packagePath).listFiles(file -> file.isDirectory() || file.getName().endsWith(END_WITH_CLASS));
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (file.isDirectory()) {
                    String subPackageName = packageName + POINT + fileName;
                    String subPackagePath = packagePath + File.pathSeparator + fileName;
                    loadClass(subPackageName, subPackagePath);
                } else {
                    Class<?> clazz = null;
                    try {
                        String className = StringUtil.append(packageName, POINT, fileName).replaceAll(END_WITH_CLASS, EMPTY_STRING);
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (clazz != null) {
                        classSet.add(clazz);
                    }
                }
            }
        }
    }
}
