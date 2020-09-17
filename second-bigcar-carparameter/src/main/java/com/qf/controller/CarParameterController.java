package com.qf.controller;

import com.qf.pojo.CarParameter;
import com.qf.service.CarParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CarParameter")
public class CarParameterController {

    @Autowired
    CarParameterService carParameterService;

    @GetMapping("/getParameter/{cb_id}")
    public CarParameter getParameter(@PathVariable("cb_id") Integer cb_id){

     return carParameterService.getAllCarParameter(cb_id);
    }

}
