package com.musikouyi.jzframe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Integer id;
    private String name;
    private String parent_role;
    private String dept;
}
