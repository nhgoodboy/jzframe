package com.musikouyi.jzframe.domain.node;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/27 23:23
 **/
@Data
@AllArgsConstructor
public class TreeNode {

    private Integer id;
    private String name;
    private String code;
    private String pCode;
    private List<TreeNode> children;
}
