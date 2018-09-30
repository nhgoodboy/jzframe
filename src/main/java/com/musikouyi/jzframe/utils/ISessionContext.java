package com.musikouyi.jzframe.utils;

import java.util.Map;

/**
 * 会话存储器.
 * 目前采用web session方式存储.
 * 也适用其它方式接口
 */
public interface ISessionContext {

	/**
	 * 存储服务器会话变量.
	 * @param key
	 * @param value
	 */
	void setServerValue(String key, Object value);
	/**
	 * 获取服务器会话变量.
	 * @param key
	 * @return
	 */
	<T>T getServerValue(String key);

	/**
	 * 获取会话请求参数.
	 * @return
	 */
	Map<String, String> getRequestMap();
	
	/**
	 * 移除服务器会话变量.
	 * @param key
	 */
	void removeServerValue(String key);
	
    /**
     * 获取客户端会话变量.（web对应Cookie）
     * @param key
     * @return
     */
    String getClientValue(String key);

    /**
     * @param key
     * @param value
     */
    void setClientValue(String key, String value);
    
    /**
     * @param cookieName
     */
    void removeClientValue(String cookieName);

	/**
	 * 请求的IP.
	 * @return
	 */
	String getRemoteIp();
    /**
     * @return 请求域名.
     */
    String getHost();

}
