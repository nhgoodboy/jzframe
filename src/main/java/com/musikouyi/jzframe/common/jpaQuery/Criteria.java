package com.musikouyi.jzframe.common.jpaQuery;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个查询条件容器
 *
 * @author Jim
 */
public class Criteria<T> implements Specification<T> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<Criterion> criterionList = new ArrayList<>();

    public Criteria() {
    }

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (!criterionList.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (Criterion c : criterionList) {
                predicates.add(c.toPredicate(root, query, builder));
            }
            // 将所有条件用 and 联合起来  
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }

    /**
     * 增加简单条件表达式
     */
    public Criteria<T> add(Criterion criterion) {
        if (criterion != null) {
            criterionList.add(criterion);
        }
        return this;
    }
}  