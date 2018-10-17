package com.musikouyi.jzframe.domain.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yjz
 * @since 2018-09-13
 */
@Data
@Entity
@Table(name = "sys_dept")
@Alias(value = "dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 父部门id
     */
    @Column(name = "PARENT_ID", nullable = false)
    private Integer parentId;
    /**
     * 简称
     */
    @Column(name = "SIMPLE_NAME")
    private String simpleName;
    /**
     * 全称
     */
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;
    /**
     * 提示
     */
    @Column(name = "TIPS")
    private String tips;
    /**
     * 版本（乐观锁保留字段）
     */
    @Column(name = "VERSION")
    private Integer version;
}
