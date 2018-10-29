package com.musikouyi.jzframe.dao.repository;

import com.musikouyi.jzframe.domain.entity.SmallPict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:34
 **/
public interface
SmallPictRepository extends JpaRepository<SmallPict, Integer> {

    List<SmallPict> findByFileInfId(Integer fileInfId);
}
