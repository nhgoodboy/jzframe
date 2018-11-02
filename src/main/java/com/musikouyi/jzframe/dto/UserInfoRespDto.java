package com.musikouyi.jzframe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 13:26
 **/
@Data
public class UserInfoRespDto {

    private String avatar;
    private String account;
    private String name;
    private String sex;
    private String role;
    private String dept;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")    //Date类型转String
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Set<String> permissions;
}
