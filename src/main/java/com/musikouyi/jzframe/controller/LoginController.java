package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.exception.GlobalException;
import com.musikouyi.jzframe.common.shiro.realm.ShiroRealm;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ControllerMapping.BASE)
public class LoginController {


    @Autowired
    private DefaultSecurityManager defaultSecurityManager;

    @GetMapping(ControllerMapping.DO_LOGIN)
    public Result doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

//        ShiroRealm shiroRealm = new ShiroRealm();
//        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        defaultSecurityManager.setRealm(shiroRealm);

//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("sha256");
//        hashedCredentialsMatcher.setHashIterations(1);
//        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        System.out.println(subject.isPermitted("user"));

        User user = SpringContextHolder.getBean(IUserService.class).getByAccount(username);
        if (user != null && password.equals(user.getPassword())) {
            Map<String, Object> result = new HashMap<>();
            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
            return ResultUtil.success(result);
        } else {
            throw new GlobalException(ResultEnum.USERNAME_PASSWORD_ERROR);
        }
    }

    @GetMapping(ControllerMapping.DO_LOGOUT)
    public Result doLogout() {
        return ResultUtil.success();
    }
}
