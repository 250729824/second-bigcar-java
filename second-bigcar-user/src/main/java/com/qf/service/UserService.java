package com.qf.service;

import com.qf.pojo.BaseResult;
import com.qf.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    String loginUser(User user);
    String findByUserMail(String usermail);
    BaseResult findUser(HttpServletRequest request);
}
