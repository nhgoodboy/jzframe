package com.musikouyi.jzframe.dto;

import lombok.Data;

@Data
public class RoleReqDto {

    private Integer id;
    private String name;
    private String parent_role;
    private String dept;
}
