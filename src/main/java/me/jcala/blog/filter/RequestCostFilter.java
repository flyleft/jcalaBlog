package me.jcala.blog.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/27.
 */
public class RequestCostFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCostFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        StopWatch stopWatch = new StopWatch(System.currentTimeMillis() + "");
        stopWatch.start();
        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();

        LOGGER.debug(httpRequest.getRequestURI() + " -> request cost - " + stopWatch.getTotalTimeMillis());
    }

    @Override
    public void destroy() {

    }
}
