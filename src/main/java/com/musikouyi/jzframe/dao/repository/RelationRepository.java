package com.musikouyi.jzframe.dao.repository;

import com.musikouyi.jzframe.domain.entity.RoleMenuRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<RoleMenuRelation, Integer> {
}
