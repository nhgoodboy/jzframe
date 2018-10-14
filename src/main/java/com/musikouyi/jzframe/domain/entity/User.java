package com.musikouyi.jzframe.domain.entity;

import com.musikouyi.jzframe.common.constant.Global;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author yjz
 * @since 2018-09-12
 */
@Entity
@Table(name = "sys_user")
@Data
//@Document(indexName = Global.ES_DEFAULT_INDEX, type = "user")  //elasticsearch
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;
    /**
     * 头像id
     */
    @Column(name = "USER_HEAD_PICT_ID", length = 10)
    private Integer userHeadPictId;
    /**
     * 账号
     */
    @Column(name = "ACCOUNT", nullable = false)
    private String account;
    /**
     * 名字
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 密码
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    /**
     * md5密码盐
     */
    @Column(name = "SALT")
    private String salt;
    /**
     * 生日
     */
    @Column(name = "BIRTHDAY")
    private Date birthday;
    /**
     * 性别（1：男 2：女）
     */
    @Column(name = "SEX", nullable = false)
    private Integer sex;
    /**
     * 电子邮件
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * 电话
     */
    @Column(name = "PHONE", length = 11)
    private String phone;
    /**
     * 角色id
     */
    @Column(name = "ROLE_ID", nullable = false)
    private Integer roleId;
    /**
     * 部门id
     */
    @Column(name = "DEPT_ID", nullable = false)
    private Integer deptId;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    @Column(name = "STATUS", nullable = false)
    private Integer status;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * 保留字段
     */
    @Column(name = "VERSION")
    private Integer version;
}
