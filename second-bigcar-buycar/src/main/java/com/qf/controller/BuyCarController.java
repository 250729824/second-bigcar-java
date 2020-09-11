package com.qf.controller;


import com.qf.pojo.BaseResult;
import com.qf.service.BuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/BuyCar")
public class BuyCarController {

    @Autowired
    BuyCarService buyCarService;
    @PostMapping("/saveCarToUser")
    public BaseResult saveCarToUser(@RequestBody Map map, HttpServletRequest request) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Integer car_id = (Integer) map.get("car_id");
        Object o = map.get("createtime");
        Date createtime = simpleDateFormat.parse(o.toString());
        return buyCarService.saveCarToUser(car_id,createtime,request);

    }
    @GetMapping("/findUserAllCar")
    public BaseResult findUserAllCar(HttpServletRequest request){

        return buyCarService.finUserAllCar(request);

    }





}
