package com.musikouyi.jzframe.repository;

import com.musikouyi.jzframe.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByAccount(String account);

    void deleteUserByAccount(String account);

//    Page<User> findAll(int page, int limit);

}
