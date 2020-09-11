package com.qf.service;


import com.qf.pojo.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface BuyCarService {


    BaseResult finUserAllCar(HttpServletRequest request);
    BaseResult saveCarToUser(Integer car_id, Date createtime, HttpServletRequest request);


}
