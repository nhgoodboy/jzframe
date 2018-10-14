package com.musikouyi.jzframe.domain.entity;

import com.musikouyi.jzframe.common.constant.Global;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;

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
//@Document(indexName = Global.ES_DEFAULT_INDEX, type = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 父角色id
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;
    /**
     * 角色名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 部门名称
     */
    @Column(name = "DEPT_ID")
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
