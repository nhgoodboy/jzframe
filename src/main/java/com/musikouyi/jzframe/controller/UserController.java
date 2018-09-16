package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.UserInfoRespDto;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 9:50
 **/
@RestController
@RequestMapping(ControllerMapping.USER)
public class UserController {

    @GetMapping("/userInfo")
    public Result userInfo(@RequestParam("token") String token) {
        System.out.println(token);
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        UserInfoRespDto userInfoRespDto = new UserInfoRespDto("admin", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif", roles);
        return ResultUtil.success(userInfoRespDto);
    }
}
