package com.qf.dao;

import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface IUnCarDao {

    List<UnCar> getAllCar();
    List<UnCar> getAllUnCars(UnCar unCar);
    UnCar getCarByCarId(Integer car_id);
    Integer insertCar(UnCar unCar);
    Set<UnCar> getBrand();



}
