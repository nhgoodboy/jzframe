package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.UserInfoReqDto;

import java.io.FileNotFoundException;

public interface IUserInfoService {

    /**
     * 修改用户信息
     */
    Result editUserInfo(UserInfoReqDto userInfoReqDto);

    /**
     * 修改用户头像
     *
     * @param userHeadId
     * @return
     */
    Result changeAvatar(Integer userHeadId, Integer userId) throws FileNotFoundException;

    /**
     * 通过userid获取用户信息
     */
    Result userInfo(Integer userId);
}
