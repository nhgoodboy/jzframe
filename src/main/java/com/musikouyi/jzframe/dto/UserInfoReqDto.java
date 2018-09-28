package com.musikouyi.jzframe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoReqDto {

    private String token;
    private String name;
    private String sex;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
}
