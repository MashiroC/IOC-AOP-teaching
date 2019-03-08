package org.redrock.shiinaframework.core;

import com.google.gson.Gson;
import org.redrock.shiinaframework.been.ResponseEntity;
import org.redrock.shiinaframework.been.ShiinaContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @author: Shiina18
 * @date: 2019/3/6 19:55
 * @description: 封装过的处理器
 */
public class Handle {
    /**
     * 全局统一的Json处理库
     */
    private static final Gson GSON = new Gson();

    /**
     * 用来处理请求的方法
     */
    private Method method;

    /**
     * 该方法所属的controller
     */
    private Object controller;

    Handle(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    /**
     * 根据上下文执行方法
     * @param context 框架的上下文
     * @return 如果有返回 那么输出到网络流
     */
    String invoke(ShiinaContext context) {
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = request.getParameter(key);
            context.putParam(key, value);
        }

        ResponseEntity res = null;
        try {
            res = (ResponseEntity) method.invoke(controller, context);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (res != null) {
            return GSON.toJson(res);
        }
        return null;
    }
}
