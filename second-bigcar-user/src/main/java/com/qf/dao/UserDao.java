package com.qf.dao;

import com.qf.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByUserMail(String usermail);
}
