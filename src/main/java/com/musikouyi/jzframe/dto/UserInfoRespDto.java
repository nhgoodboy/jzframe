package com.musikouyi.jzframe.dto;

import lombok.Data;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 13:26
 **/
@Data
public class UserInfoRespDto {

    private String name;
    private String avatar;
    private List<String> roles;

    public UserInfoRespDto(String name, String avatar, List<String> roles) {
        this.name = name;
        this.avatar = avatar;
        this.roles = roles;
    }
}
