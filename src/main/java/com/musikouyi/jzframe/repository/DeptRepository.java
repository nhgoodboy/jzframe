package com.musikouyi.jzframe.repository;

import com.musikouyi.jzframe.domain.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

    @Query("select d.fullname from Dept d")
    List<String> findName();
}
