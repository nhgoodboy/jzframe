package com.musikouyi.jzframe.dao.repository;

import com.musikouyi.jzframe.domain.entity.FileInf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileInfRepository extends JpaRepository<FileInf, Integer>, JpaSpecificationExecutor<FileInf> {

    
}
