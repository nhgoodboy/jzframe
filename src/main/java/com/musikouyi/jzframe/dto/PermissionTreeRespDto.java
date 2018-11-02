package com.musikouyi.jzframe.dto;

import com.musikouyi.jzframe.domain.node.TreeNode;
import lombok.Data;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/28 14:11
 **/
@Data
public class PermissionTreeRespDto {

    private List<TreeNode> permissionsList;
    private List<Integer> checkedPermissionIds;
}
