package com.musikouyi.jzframe.domain.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_permission")
@Alias(value = "permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * 权限编号
     */
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;
    /**
     * 权限父编号
     */
    @Column(name = "P_CODE", nullable = false)
    private String pCode;
    /**
     * 权限名称
     */
    @Column(name = "NAME", nullable = false)
    private String name;
    /**
     * 权限排序号
     */
    @Column(name = "NUM", nullable = false)
    private Integer num;
    /**
     * 权限层级
     */
    @Column(name = "LEVELS", nullable = false)
    private Integer levels;
    /**
     * 备注
     */
    @Column(name = "TIPS")
    private String tips;
    /**
     * 权限状态 :  1:启用   0:不启用
     */
    @Column(name = "STATUS")
    private Integer status;
}
