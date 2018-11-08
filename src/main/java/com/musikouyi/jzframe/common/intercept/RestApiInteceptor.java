package com.musikouyi.jzframe.common.intercept;

import com.alibaba.fastjson.JSON;
import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.constant.JwtConstants;
import com.musikouyi.jzframe.common.exception.GlobalException;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.ResultUtil;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Rest Api接口鉴权
 *
 * @author yjz
 * @Date 2018/09/15 23:11
 */
public class RestApiInteceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(RestApiInteceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {  //跳过静态资源请求验证
            return true;
        }
        logger.info("rest api鉴权拦截");
        return check(request, response);
    }

    /**
     * token验证
     *
     * @param request
     * @param response
     * @return
     */
    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        final String requestMethod = request.getMethod();
        if (Global.OPTIONS_REQUEST.equals(requestMethod)) {  //跳过option请求验证
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
//                    throw new GlobalException(ResultEnum.TOKEN_EXPIRED);   //此方法存在跨域问题，待探究
                    renderJson(response, ResultUtil.error(ResultEnum.TOKEN_EXPIRED));
                    return false;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
//                throw new GlobalException(ResultEnum.TOKEN_ERROR);
                renderJson(response, ResultUtil.error(ResultEnum.TOKEN_ERROR));
                return false;
            }
        } else {
            //header没有带Bearer字段
//            throw new GlobalException(ResultEnum.TOKEN_WITHOUT);
            renderJson(response, ResultUtil.error(ResultEnum.TOKEN_WITHOUT));
            return false;
        }
        request.getSession().setAttribute(JwtConstants.TOKEN_SESSION, authToken);   //在session中保存token
        return true;
    }

    /**
     * 渲染json对象
     */
    private void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:9528");  //解决跨域问题
            response.addHeader("Access-Control-Allow-Credentials", "true");  //解决跨域问题
            PrintWriter writer = response.getWriter();
            System.out.println(JSON.toJSONString(jsonObject));
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new GlobalException(ResultEnum.UNKNOWN_ERROR);
        }
    }

}
