package com.musikouyi.jzframe.common.intercept;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.constant.JwtConstants;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.exception.GlobalException;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Rest Api接口鉴权
 *
 * @author yjz
 * @Date 2018/09/15 23:11
 */
public class RestApiInteceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(RestApiInteceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
        logger.info("rest api鉴权拦截");
        return check(request, response);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        final String requestMethod = request.getMethod();
        if (Global.OPTIONS_REQUEST.equals(requestMethod)) {
            return true;
        }
        final String requestHeader = request.getHeader(JwtConstants.AUTH_HEADER);
        String authToken;
        if (requestHeader != null && requestHeader.startsWith(JwtConstants.TOKEN_PREFIX)) {
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = JwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    throw new GlobalException(ResultEnum.TOKEN_EXPIRED);
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                throw new GlobalException(ResultEnum.TOKEN_ERROR);
            }
        } else {
            //header没有带Bearer字段
            throw new GlobalException(ResultEnum.TOKEN_WITHOUT);
        }
        request.getSession().setAttribute(JwtConstants.TOKEN_SESSION, authToken);   //在session中保存token
        return true;
    }

}
