package com.qf.controller;

import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;
import com.qf.service.IUnCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/UnCar")
public class UnCarController {

    @Autowired
    IUnCarService unCarService;

    @GetMapping("/getAllUnCar/{page}/{size}")
    public BaseResult getAllUnCar(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        BaseResult baseResult = unCarService.getAllCar(page, size);
        return baseResult;

    }
    @PostMapping("/getAllCar")
    public BaseResult getAllCar(@RequestBody UnCar unCar){
        BaseResult allUnCars = unCarService.getAllUnCars(unCar);
        return allUnCars;
    }

    @GetMapping("/getCarByCarId/{car_id}")
    public UnCar getCarByCarId(@PathVariable("car_id") Integer car_id){
        return  unCarService.getCarByCarId(car_id);
    }

}
