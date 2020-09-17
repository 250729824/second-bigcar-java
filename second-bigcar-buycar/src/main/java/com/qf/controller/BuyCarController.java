package com.qf.controller;


import com.qf.pojo.BaseResult;
import com.qf.service.BuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/BuyCar")
public class BuyCarController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BuyCarService buyCarService;
    @PostMapping("/saveCarToUser")
    public BaseResult saveCarToUser(@RequestBody Map map, HttpServletRequest request) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Integer car_id = (Integer) map.get("car_id");
        Integer cb_id = (Integer) map.get("cb_id");
        Object o = map.get("createtime");
        Date createtime = simpleDateFormat.parse(o.toString());
        long time = createtime.getTime()+1000*24*60*60;
        Date date = new Date(time);
        System.out.println(date);
        return buyCarService.saveCarToUser(car_id,cb_id,date,request);
//1599753600000
    }
    @GetMapping("/findUserAllCar")
    public BaseResult findUserAllCar(HttpServletRequest request){

        return buyCarService.finUserAllCar(request);

    }

    @GetMapping("/findAppointment")
    public Map<String,Object> findAppointment(){

        Map map = new HashMap();
        List user_cars = redisTemplate.opsForHash().values("user_cars");
        Set user_cars1 = redisTemplate.opsForHash().keys("user_cars");

        map.put("user_cars",user_cars);
        map.put("user",user_cars1);

        return map;
    }





}
