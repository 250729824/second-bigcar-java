package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.ICarParameterDao;
import com.qf.pojo.BaseResult;
import com.qf.pojo.CarParameter;
import com.qf.pojo.UnCar;
import com.qf.pojo.User;
import com.qf.service.CarParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CarParameterServiceImpl implements CarParameterService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ICarParameterDao carParameterDao;

    @Override
    public CarParameter getAllCarParameter(Integer cb_id) {
        Boolean cars = redisTemplate.hasKey("cars_parameters_"+cb_id);
        if(cars){
            System.out.println("从redis中获取");
            Object o = redisTemplate.opsForValue().get("cars_parameters_" + cb_id);
            Object o1 = JSONObject.toJSON(o);
            CarParameter carParameter = JSONObject.parseObject(o1.toString(), CarParameter.class);
            return carParameter;
        }else{
            System.out.println("从数据库中获取");
            CarParameter allCarParameter = carParameterDao.getAllCarParameter(cb_id);
            Long i = (long)(Math.random() * (2-1+1) + 1);
            redisTemplate.opsForValue().set("cars_parameters_"+cb_id,allCarParameter,i, TimeUnit.HOURS);
            return allCarParameter;

        }

    }
}
