package com.latico.example.springboot;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import javax.servlet.http.HttpServletRequest;

public class CustomRouteLocator extends SimpleRouteLocator {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(CustomRouteLocator.class);


    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    @Override
    public Route getMatchingRoute(final String path) {
        LOG.info("Invoking CustomRouteLocator getMatchingRoute method. {}", path);

        Route route = getSimpleMatchingRoute(path);
        LOG.info("原路由:{}", route);
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String wd = request.getParameter("wd");
        LOG.info("wd:{}", wd);

        //一般是根据携带的参数来判断替换主机定位，不改变path，下面为了测试效果，把fullpath都替换了
        // route.setLocation("https://www.baidu.com");
        route.setLocation("http://localhost:5400");
        // route.setFullPath("/s?wd=" + wd);
        // route.setPath("/s?wd=" + wd);
        //http://localhost:8080/sousuo?wd=%E4%B8%AD%E5%9B%BD
        //https://www.baidu.com/s?wd=%E4%B8%AD%E5%9B%BD
        // LOG.info("uri is {}, location is {}, apiName is {}.", route.getPath(), route.getLocation(), apiName);
        // return new Route(apiname, "/", "https://www.baidu.com", "",
        //         true,
        //         null,
        //         false);

        LOG.info("最终:{}", route);
        return route;
    }
}