package com.musikouyi.jzframe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 20:30
 **/
@Data
@AllArgsConstructor
public class UserRespDto {

    private int id;
    private String account;
    private String name;
    private String sex;
    private String role;
    private String dept;
    private String email;
    private String phone;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")    //Date类型转String
    private Date createtime;
    private String status;

}
