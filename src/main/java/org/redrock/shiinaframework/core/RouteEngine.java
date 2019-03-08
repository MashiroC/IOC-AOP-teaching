package org.redrock.shiinaframework.core;

import org.redrock.shiinaframework.annotation.RequestMapper;
import org.redrock.shiinaframework.annotation.HttpMethod;
import org.redrock.shiinaframework.been.RouteInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Shiina18
 * @date: 2019/3/6 19:45
 * @description: 路由引擎
 */
public class RouteEngine {
    private static RouteEngine instance = null;
    private BeenFactory beenFactory = null;

    /**
     * 用来存放method和uri对处理器的映射
     */
    private Map<RouteInfo, Handle> routeMap;

    public static RouteEngine getInstance() {
        if (instance == null) {
            synchronized (RouteEngine.class) {
                if (instance == null) {
                    instance = new RouteEngine();
                }
            }
        }
        return instance;
    }

    private RouteEngine() {
        init();
    }

    private void init() {
        beenFactory = BeenFactory.getInstance();
        routeMap = new HashMap<>(20);
        loadRoute();
    }

    /**
     * 加载路由
     */
    private void loadRoute() {
        Map<Class<?>, Object> controlleMap = beenFactory.getControllerMap();
        String prefix = "";
        for (Map.Entry<Class<?>, Object> entry : controlleMap.entrySet()) {
            Class<?> clazz = entry.getKey();
            Object controller = entry.getValue();

            RequestMapper controllerMapper = clazz.getAnnotation(RequestMapper.class);
            if (controllerMapper != null) {
                controllerMapper.value();
                prefix = controllerMapper.value();
            }

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
    }

    public Handle getHandle(RouteInfo route) {
        return routeMap.get(route);
    }
}
