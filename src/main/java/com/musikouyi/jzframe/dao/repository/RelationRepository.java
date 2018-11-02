package com.musikouyi.jzframe.dao.repository;

import com.musikouyi.jzframe.domain.entity.RolePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<RolePermissionRelation, Integer> {
}
