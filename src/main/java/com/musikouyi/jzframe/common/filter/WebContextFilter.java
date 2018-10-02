package com.musikouyi.jzframe.common.filter;

import com.musikouyi.jzframe.utils.WebContextHolder;
import com.musikouyi.jzframe.utils.WebSessionContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component
//@WebFilter(filterName = "webContextFilter", urlPatterns = "/")
public class WebContextFilter implements Filter {

    public void init(FilterConfig filterConfig) {
        System.out.println("init");
        WebContextHolder.setContextPath(filterConfig.getServletContext().getContextPath());
        WebContextHolder.setWarPath(filterConfig.getServletContext().getRealPath("/"));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("WebContextFilter.doFilter");
        WebContextHolder.setSessionContextStore(new WebSessionContext((HttpServletRequest) request, (HttpServletResponse) response));
        chain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("WebContextFilter.destroy");
    }
}
