package com.musikouyi.jzframe.domain.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_menu")
@Alias(value = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * 菜单编号
     */
    @Column(name = "CODE", nullable = false)
    private String code;
    /**
     * 菜单父编号
     */
    @Column(name = "P_CODE", nullable = false)
    private String pCode;
    /**
     * 当前菜单的所有父菜单编号
     */
    @Column(name = "P_CODES", nullable = false)
    private String pCodes;
    /**
     * 菜单名称
     */
    @Column(name = "NAME", nullable = false)
    private String name;
    /**
     * url地址
     */
    @Column(name = "URL", nullable = false)
    private String url;
    /**
     * 菜单排序号
     */
    @Column(name = "NUM", nullable = false)
    private Integer num;
    /**
     * 菜单层级
     */
    @Column(name = "LEVELS", nullable = false)
    private Integer levels;
    /**
     * 是否是菜单（1：是  0：不是）
     */
    @Column(name = "IS_MENU", nullable = false)
    private Integer isMenu;
    /**
     * 备注
     */
    @Column(name = "TIPS")
    private String tips;
    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    @Column(name = "STATUS", nullable = false)
    private Integer status;
    /**
     * 是否打开:    1:打开   0:不打开
     */
    @Column(name = "IS_OPEN", nullable = false)
    private Integer isOpen;
}
