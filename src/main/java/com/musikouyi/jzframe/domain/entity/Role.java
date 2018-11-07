package com.musikouyi.jzframe.domain.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

//import org.springframework.data.elasticsearch.annotations.Document;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yjz
 * @since 2018-09-13
 */
@Data
@Entity
@Table(name = "sys_role")
@Alias(value = "role")
//@Document(indexName = Global.ES_DEFAULT_INDEX)
//@Setting(settingPath = Global.ES_DEFAULT_ANALYSER)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @org.springframework.data.annotation.Id
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_ID", nullable = false)
    private Integer deptId;

    /**
     * 提示
     */
    @Column(name = "TIPS")
    private String tips;

    /**
     * 保留字段(暂时没用）
     */
    @Column(name = "VERSION")
    private Integer version;
}
