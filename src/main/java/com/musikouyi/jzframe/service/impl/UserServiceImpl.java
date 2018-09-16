package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.dto.UserInfoRespDto;
import com.musikouyi.jzframe.repository.UserRepository;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import com.musikouyi.jzframe.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int setStatus(Integer userId, int status) {
        return 0;
    }

    @Override
    public int changePwd(Integer userId, String pwd) {
        return 0;
    }

    @Override
    public int setRoles(Integer userId, String roleIds) {
        return 0;
    }

    @Override
    public User getByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }

    @Override
    public Result findById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        Integer[] roleArray = ToolUtil.toIntArray(user.getRoleid());
        List<String> roleNameList = new ArrayList<>();
        for (int roleId : roleArray) {
            roleNameList.add(SpringContextHolder.getBean(IRoleService.class).findById(roleId).getTips());
        }
        UserInfoRespDto userInfoRespDto = new UserInfoRespDto(user.getName(), user.getAvatar(), roleNameList);
        return ResultUtil.success(userInfoRespDto);
    }
}
