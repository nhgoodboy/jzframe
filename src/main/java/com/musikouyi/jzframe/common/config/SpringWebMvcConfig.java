package com.musikouyi.jzframe.common.config;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.intercept.RestApiInteceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

    @Value("${shiro.originUrl}")
    private String originsUrl;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

   /**
     * 解决跨域访问
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins(originsUrl)
                .allowedMethods("*")
                .allowCredentials(true)   // 解决跨域请求sessionId不一致的问题
                .maxAge(3600);
    }

    /**
     * 增加对rest api鉴权的spring mvc拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestApiInteceptor()).addPathPatterns(ControllerMapping.ADMIN_BASE + "/**");
    }

    /**
     * 定义外部资源映射目录
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + Global.uploadFolder);
    }
}
