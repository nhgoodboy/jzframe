package com.musikouyi.jzframe.repository;

import com.musikouyi.jzframe.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r.name from Role r")
    List<String> findName();

    @Query("select r.id from Role r where r.name = :name")
    Integer findIdByName(@Param("name") String name);
}
