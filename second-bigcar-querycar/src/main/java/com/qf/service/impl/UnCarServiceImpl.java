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
import java.util.*;

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
    public BaseResult getAllUnCars( int page, int size,UnCar unCar) {

        Boolean carllist = redisTemplate.hasKey("cars_"+unCar.getCar_brand()+"_"+unCar.getCar_series());
        List list = new ArrayList();
        Integer start = (page -1) * size;
        Long total;
        if(carllist){
            System.out.println("筛选redis中获取");
            list =redisTemplate.opsForList().range("cars_"+unCar.getCar_brand()+"_"+unCar.getCar_series(),start,start+size-1);
            total = redisTemplate.opsForList().size("cars_"+unCar.getCar_brand()+"_"+unCar.getCar_series());
        }else{
            System.out.println("筛选数据库中获取");
            List<UnCar> cars = unCarDao.getAllUnCars(unCar);
            System.out.println("========>"+cars);
            for (UnCar car:cars) {
                redisTemplate.opsForList().rightPush("cars_"+unCar.getCar_brand()+"_"+unCar.getCar_series(),car);
            }
            list =redisTemplate.opsForList().range("cars_"+unCar.getCar_brand()+"_"+unCar.getCar_series(),start,start+size-1);
            total = redisTemplate.opsForList().size("cars_"+unCar.getCar_brand()+"_"+unCar.getCar_series());

        }

        BaseResult baseResult = new BaseResult();
        baseResult.setList(list);
        baseResult.setTotal(total);

        return baseResult;

    }

    @Override
    public UnCar getCarByCarId(Integer car_id) {

      return unCarDao.getCarByCarId(car_id);

    }

    @Override
    public Integer insertCar(UnCar unCar) {
        return unCarDao.insertCar(unCar);
    }

    @Override
    public Map<String,Set<String>> getBrand() {

        Map<String,Set<String>> map = new HashMap<>();
        Boolean brand1 = redisTemplate.hasKey("brand");
        Boolean series = redisTemplate.hasKey("series");

        if(brand1 && series){

            Set brand =redisTemplate.opsForSet().members("brand");

            Set series1 = redisTemplate.opsForSet().members("series");

            map.put("brand",brand);
            map.put("series",series1);

            return map;
        }
        Set<UnCar> brand = unCarDao.getBrand();
        for (UnCar u :brand) {
            redisTemplate.opsForSet().add("brand",u.getCar_brand());
            redisTemplate.opsForSet().add("series",u.getCar_series());
        }
        Set brand2 =redisTemplate.opsForSet().members("brand");
        Set series1 = redisTemplate.opsForSet().members("series");
        map.put("brand",brand2);
        map.put("series",series1);
        return map;

    }


}
