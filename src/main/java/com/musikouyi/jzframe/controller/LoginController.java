package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.apache.shiro.authc.UsernamePasswordToken;
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
    private Subject subject;

    @GetMapping(ControllerMapping.DO_LOGIN)
    public Result doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        subject.login(token);

        User user = SpringContextHolder.getBean(IUserService.class).getByAccount(username);
//        if (user != null && password.equals(user.getPassword())) {
        Map<String, Object> result = new HashMap<>();
        result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
        return ResultUtil.success(result);
//        } else {
//            throw new GlobalException(ResultEnum.USERNAME_PASSWORD_ERROR);
//        }
    }

    @GetMapping(ControllerMapping.DO_LOGOUT)
    public Result doLogout() {
        return ResultUtil.success();
    }
}
