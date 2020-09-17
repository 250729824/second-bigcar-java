package com.qf.service;

import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUnCarService {

    BaseResult getAllCar(int page,int size);
    BaseResult getAllUnCars(int page, int size,UnCar unCar);
    UnCar getCarByCarId(Integer car_id);
    Integer insertCar(UnCar unCar);
    Map<String,Set<String>> getBrand();
}
