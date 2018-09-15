package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.repository.UserRepository;
import com.musikouyi.jzframe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findUserByAccount(account);
        return user;
    }
}
