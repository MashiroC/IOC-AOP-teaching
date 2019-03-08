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
 * @description:
 */
public class Handle {
    private static final Gson gson = new Gson();
    private Method method;
    private Object controller;

    Handle(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

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
            return gson.toJson(res);
        }
        return null;
    }
}
