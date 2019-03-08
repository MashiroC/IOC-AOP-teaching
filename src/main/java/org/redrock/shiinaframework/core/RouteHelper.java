package org.redrock.shiinaframework.core;

import org.redrock.shiinaframework.annotation.Controller;
import org.redrock.shiinaframework.annotation.RequestMapper;
import org.redrock.shiinaframework.annotation.HttpMethod;
import org.redrock.shiinaframework.been.RouteInfo;
import org.redrock.shiinaframework.util.ReflectUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Shiina18
 * @date: 2019/3/6 19:45
 * @description:
 */
public class RouteHelper {
    private static RouteHelper instance = null;
    private ClassLoader classLoader;

    private Map<Class<?>, Object> controllerMap;

    private Map<RouteInfo, Handle> routeMap;

    public static RouteHelper getInstance() {
        if (instance == null) {
            synchronized (RouteHelper.class) {
                if (instance == null) {
                    instance = new RouteHelper();
                }
            }
        }
        return instance;
    }

    private RouteHelper() {
        classLoader = ClassLoader.getInstance();
        routeMap = new HashMap<>(20);
        controllerMap=new HashMap<>(10);
        init();
    }

    private void init() {
        Set<Class<?>> classSet = classLoader.getClassSet();
        String prefix = "";
        for (Class<?> clazz : classSet) {
            Controller controller = clazz.getAnnotation(Controller.class);
            if (controller != null) {

                Object target = ReflectUtil.newInstance(clazz);
                controllerMap.put(clazz, target);

                RequestMapper controllerMapper = clazz.getAnnotation(RequestMapper.class);
                if (controllerMapper != null) {
                    controllerMapper.value();
                    prefix = controllerMapper.value();
                }

                loadMethod(clazz, target, prefix);

            }
        }
    }

    private void loadMethod(Class<?> clazz, Object controller, String prefix) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            RequestMapper mapper = method.getAnnotation(RequestMapper.class);
            if (mapper != null) {
                String uri = mapper.value();
                HttpMethod httpMethod = mapper.method();
                RouteInfo route = new RouteInfo(httpMethod, prefix + uri);
                Handle handle = new Handle(controller, method);
                routeMap.put(route, handle);
            }
        }
    }

    public Handle getHandle(RouteInfo route) {
        return routeMap.get(route);
    }
}
