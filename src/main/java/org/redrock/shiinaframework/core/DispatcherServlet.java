package org.redrock.shiinaframework.core;

import org.redrock.shiinaframework.annotation.HttpMethod;
import org.redrock.shiinaframework.been.RouteInfo;
import org.redrock.shiinaframework.been.ShiinaContext;
import org.redrock.shiinaframework.util.StreamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Shiina18
 * @date: 2019/3/5 21:31
 * @description: 单一入口Servlet
 */
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    private PropsLoader propsLoader = null;
    private ClassLoader classLoader = null;
    private BeenFactory beenFactory = null;
    private RouteEngine routeEngine = null;

    @Override
    public void init() {
        PropsLoader.init(getServletContext());
        propsLoader = PropsLoader.getInstance();
        classLoader = ClassLoader.getInstance();
        beenFactory = BeenFactory.getInstance();
        routeEngine = RouteEngine.getInstance();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String res;

        HttpMethod method = HttpMethod.valueOf(req.getMethod());
        String[] uriInfo = req.getRequestURI().split("\\?");
        RouteInfo routeInfo = new RouteInfo(method, uriInfo[0]);
        if (uriInfo.length > 1) {
            routeInfo.setUriParam(uriInfo[1]);
        }

        Handle handle = routeEngine.getHandle(routeInfo);

        if (handle == null) {
            res = "404 NOT FOUNT";
        } else {
            try {
                ShiinaContext context = new ShiinaContext(req, resp, routeInfo);
                res = handle.invoke(context);
            } catch (Exception e) {
                res = e.getMessage();
            }
        }

        if (res != null) {
            StreamUtil.writeStream(resp.getOutputStream(), res);
        } else {
            resp.getOutputStream().close();
        }
    }


}
