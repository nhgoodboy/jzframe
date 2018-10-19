package com.musikouyi.jzframe.common.filter;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.utils.WebContextHolder;
import com.musikouyi.jzframe.utils.WebSessionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(filterName = "webContextFilter", urlPatterns = "/")
public class WebContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("WebContextFilter.init");
        WebContextHolder.setContextPath(filterConfig.getServletContext().getContextPath());
//        WebContextHolder.setWarPath(filterConfig.getServletContext().getRealPath("/"));
        try {
            WebContextHolder.setWarPath(ResourceUtils.getURL(Global.CLASSPATH_STATIC_DIR).getPath().substring(1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("WebContextFilter.doFilter");
        WebContextHolder.setSessionContextStore(new WebSessionContext((HttpServletRequest) request, (HttpServletResponse) response));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("WebContextFilter.destroy");
    }
}
