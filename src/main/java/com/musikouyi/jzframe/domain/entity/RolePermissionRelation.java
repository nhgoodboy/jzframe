package com.musikouyi.jzframe.domain.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SYS_ROLE_PERMISSION_RELATION")
@Alias(value = "rolePermissionRelation")
public class RolePermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * 权限id
     */
    @Column(name = "PERMISSION_ID", nullable = false)
    private Integer permissionId;

    /**
     * 角色id
     */
    @Column(name = "ROLE_ID", nullable = false)
    private Integer roleId;
}
