package com.qf.feign;


import com.qf.pojo.UnCar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(serviceId = "second-bigcar-querycar")
public interface CarsClient {

    @GetMapping("/UnCar/getCarByCarId/{car_id}")
    public UnCar getCarByCarId(@PathVariable("car_id") Integer car_id);

}

