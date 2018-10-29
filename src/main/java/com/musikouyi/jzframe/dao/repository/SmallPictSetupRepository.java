package com.musikouyi.jzframe.dao.repository;

import com.musikouyi.jzframe.domain.entity.SmallPictSetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 20:53
 **/
public interface SmallPictSetupRepository extends JpaRepository<SmallPictSetup, Integer> {

    List<SmallPictSetup> findByBusinessClassNmAndBusinessFieldNm(String businessClassNm, String businessFieldNm);
}
