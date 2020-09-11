package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.IUnCarDao;
import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;
import com.qf.service.IUnCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnCarServiceImpl implements IUnCarService {

    @Autowired
    IUnCarDao unCarDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public BaseResult getAllCar(int page, int size) {

        Boolean carllist = redisTemplate.hasKey("carlist");
        List list = new ArrayList();
        Integer start = (page -1) * size;
        Long total;
        if(carllist){
            System.out.println("从redis中获取");
            list =redisTemplate.opsForList().range("carlist",start,start+size-1);
            total = redisTemplate.opsForList().size("carlist");
        }else{
            System.out.println("从数据库中获取");
            List<UnCar> cars = unCarDao.getAllCar();
            System.out.println("========>"+cars);
            for (UnCar car:cars) {
                redisTemplate.opsForList().rightPush("carlist",car);
            }
            list =redisTemplate.opsForList().range("carlist",start,start+size-1);
            total = redisTemplate.opsForList().size("carlist");

        }

                BaseResult baseResult = new BaseResult();
                baseResult.setList(list);
                baseResult.setTotal(total);

        return baseResult;
    }

    @Override
    public BaseResult getAllUnCars( UnCar unCar) {

        BaseResult baseResult = new BaseResult();
        List<UnCar> allUnCars = unCarDao.getAllUnCars(unCar);

        baseResult.setList(allUnCars);
        return baseResult;

    }

    @Override
    public UnCar getCarByCarId(Integer car_id) {

      return unCarDao.getCarByCarId(car_id);

    }







}
