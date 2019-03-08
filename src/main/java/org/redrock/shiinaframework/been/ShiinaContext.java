package org.redrock.shiinaframework.been;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Shiina18
 * @date: 2019/3/7 15:16
 * @description: 框架的上下文
 */
public class ShiinaContext {

    private HttpServletRequest request;

    private HttpServletResponse response;

    /**
     * 参数map
     */
    private Map<String,String> paramMap;

    private RouteInfo routeInfo;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public ShiinaContext(HttpServletRequest request, HttpServletResponse response,RouteInfo routeInfo){
        this.request=request;
        this.response = response;
        this.routeInfo=routeInfo;
        this.paramMap=new HashMap<>();
    }

    public void putParam(String key,String value){
        this.paramMap.put(key,value);
    }

    public String getParam(String key){
        return this.paramMap.get(key);
    }

}
