package com.qf.service;

import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUnCarService {

    BaseResult getAllCar(int page,int size);
    BaseResult getAllUnCars(UnCar unCar);
    UnCar getCarByCarId(Integer car_id);
}
