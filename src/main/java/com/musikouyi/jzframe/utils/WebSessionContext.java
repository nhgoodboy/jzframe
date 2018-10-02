package com.musikouyi.jzframe.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class WebSessionContext implements ISessionContext {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String remoteIp;

    public WebSessionContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void setServerValue(String key, Object value) {
        request.getSession(true).setAttribute(key, value);
    }

    @Override
    public Object getServerValue(String key) {
        return request.getSession().getAttribute(key);
    }

    @Override
    public void removeServerValue(String key) {
        request.getSession().removeAttribute(key);
    }

    @Override
    public String getClientValue(String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(key)) {
                return cookie.getValue() == null ? "" : cookie.getValue();
            }
        }
        return "";
    }

    @Override
    public void setClientValue(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(315360000); //10 years
        cookie.setDomain(getHost());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void removeClientValue(String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(key)) {
                cookie.setMaxAge(-1);
                cookie.setDomain(getHost());
                cookie.setPath("/");
                response.addCookie(cookie);
                return;
            }
        }
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public String getRemoteIp() {
        if (remoteIp == null) {
            remoteIp = getIpAddress(request);
        }
        return remoteIp;
    }

    @Override
    public String getHost() {
        return request.getHeader("host");
    }

    @Override
    public Map<String, String> getRequestMap() {
        Map<String, String> requestMap = new HashMap<String, String>();
        for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            requestMap.put(entry.getKey(), entry.getValue()[0]);
        }
        return requestMap;
    }

    @Override
    public String getSessionId() {
        return request.getSession().getId();
    }

    @Override
    public void invalidate() {
        request.getSession().invalidate();
    }


}
