package com.qf.feign;


import com.qf.pojo.UnCar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(serviceId = "second-bigcar-querycar")
public interface CarsClient {

    @GetMapping("/UnCar/getCarByCarId/{car_id}/{cb_id}")
    public Map getCarByCarId(@PathVariable("car_id") Integer car_id, @PathVariable("cb_id") Integer cb_id);

}

