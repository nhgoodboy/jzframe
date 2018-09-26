package com.musikouyi.jzframe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleRespDto {

    private Integer id;
    private String name;
    private String parent_role;
    private String dept;
}
