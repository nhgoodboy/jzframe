package com.musikouyi.jzframe.common.config;

import com.musikouyi.jzframe.common.shiro.realm.ShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    /**
     * shiro的realm配置
     *
     * @return
     */
    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }
//
//    @Bean
//    public DefaultSecurityManager defaultSecurityManager() {
//        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        defaultSecurityManager.setRealm(realm());
//        return defaultSecurityManager;
//    }

//    @ModelAttribute(name = "subject")
//    public Subject subject() {
//        SecurityUtils.setSecurityManager(getDefaultSecurityManager());
//        return SecurityUtils.getSubject();
//    }
}
