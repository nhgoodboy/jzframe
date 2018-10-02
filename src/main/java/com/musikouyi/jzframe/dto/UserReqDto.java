package com.musikouyi.jzframe.dto;

import lombok.Data;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/22 20:03
 **/

@Data
public class UserReqDto {

    private Integer id;
    private String account;
    private String name;
    private String sex;
    private String role;
    private String dept;
    private String email;
    private String phone;
    private String status;
    private String password;
    private String confirm_pwd;
}
