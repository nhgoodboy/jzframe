package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.exception.GlobalException;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
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
@Slf4j
public class LoginController {

    @Autowired
    private Subject subject;

    @GetMapping(ControllerMapping.DO_LOGIN)
    public Result doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        ensureUserIsLoggedOut();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new GlobalException(ResultEnum.USERNAME_PASSWORD_ERROR);
        }

        User user = SpringContextHolder.getBean(IUserService.class).getByAccount(username);
        Map<String, Object> result = new HashMap<>();
        result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
        return ResultUtil.success(result);
    }

    // Logout the user fully before continuing.(解决sessionId过期的问题)
    private void ensureUserIsLoggedOut() {
        try {
            // Get the user if one is logged in.
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser == null){
                return;
            }
            // Log the user out and kill their session if possible.
            currentUser.logout();
            Session session = currentUser.getSession(false);
            if (session == null){
                return;
            }
            session.stop();
        } catch (Exception e) {
            // Ignore all errors, as we're trying to silently
            // log the user out.
            log.info(String.valueOf(e));
        }
    }

    @GetMapping(ControllerMapping.DO_LOGOUT)
    public Result doLogout() {
        ensureUserIsLoggedOut();
        return ResultUtil.success();
    }
}
