package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.feign.CarsClient;
import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;
import com.qf.pojo.User;
import com.qf.service.BuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class BuyServiceImpl implements BuyCarService {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CarsClient carsClient;




    @Override
    public BaseResult finUserAllCar(HttpServletRequest request) {

        User user = findUserByToken(request);
        if(redisTemplate.hasKey("cars_"+user.getCode())){
            List list = redisTemplate.opsForHash().values("cars_" + user.getCode());
            BaseResult baseResult = new BaseResult();
            baseResult.setList(list);
            return baseResult;
        }

        return null;
    }

    @Override
    public BaseResult saveCarToUser(Integer car_id, Date createtime, HttpServletRequest request) {
        //获取当前操作用户
        User user = findUserByToken(request);
        //获取商品的信息
        UnCar unCar = carsClient.getCarByCarId(car_id);
        unCar.setCreatetime(createtime);
        //声明返回值
        BaseResult baseResult = new BaseResult();

        try{
            //操作redis
            redisTemplate.opsForHash().put("cars_"+user.getCode(),unCar.getCar_id().toString(),unCar);
        }catch (Exception e){
            System.out.println("======>"+e.getMessage());
            baseResult.setMessage("预约失败");
            return baseResult;
        }
        baseResult.setMessage("预约成功");
        return baseResult;
    }

    public User findUserByToken(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        String token = getToken(cookies);
        Object o = redisTemplate.opsForValue().get(token);
        Object o1 = JSONObject.toJSON(o);
        User user = JSONObject.parseObject(o1.toString(), User.class);
        return user;
    }

    public String getToken(Cookie[] cookies){

        if(cookies!=null){
            for (Cookie c:cookies){
                if(c.getName().equals("token")){
                    String value = c.getValue();
                    return value;
                }
            }
            return null;
        }
        return null;
    }
}
