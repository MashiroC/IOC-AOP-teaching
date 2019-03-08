package org.redrock.shiinaframework.been;

import lombok.Data;
import org.redrock.shiinaframework.annotation.HttpMethod;

import java.util.Objects;

/**
 * @author: Shiina18
 * @date: 2019/3/6 20:27
 * @description: 路由信息
 */
public class RouteInfo {

    private String uri;

    private HttpMethod method;

    private String uriParam;

    public String getUri() {
        return uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    String getUriParam() {
        return uriParam;
    }

    public void setUriParam(String uriParam) {
        this.uriParam = uriParam;
    }

    public RouteInfo(HttpMethod method, String uri){
        this.method=method;
        this.uri=uri;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteInfo routeInfo = (RouteInfo) o;
        return Objects.equals(uri, routeInfo.uri) &&
                method == routeInfo.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, method);
    }
}
