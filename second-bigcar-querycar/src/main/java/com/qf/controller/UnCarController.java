package com.qf.controller;

import com.alibaba.fastjson.JSONObject;
import com.qf.feign.CarParameterClient;
import com.qf.pojo.BaseResult;
import com.qf.pojo.UnCar;
import com.qf.pojo.User;
import com.qf.service.IUnCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.POSITIVE_INFINITY;

@RestController
@RequestMapping("/UnCar")
public class UnCarController {

    @Autowired
    IUnCarService unCarService;

    @Autowired
    CarParameterClient carParameterClient;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/getAllUnCar/{page}/{size}")
    public BaseResult getAllUnCar(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        BaseResult baseResult = unCarService.getAllCar(page, size);
        return baseResult;

    }
    @PostMapping("/getAllCar/{page}/{size}")
    public BaseResult getAllCar(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@RequestBody UnCar unCar){
        BaseResult allUnCars = unCarService.getAllUnCars(page,size,unCar);
        return allUnCars;
    }

    @GetMapping("/getCarByCarId/{car_id}/{cb_id}")
    public Map getCarByCarId(@PathVariable("car_id") Integer car_id,@PathVariable("cb_id") Integer cb_id){


        UnCar carByCarId = unCarService.getCarByCarId(car_id);
        Map<String,Object> map = new HashMap();
        map.put("uncar",carByCarId);
        map.put("carparameter",carParameterClient.getParameter(cb_id));

        hotCarPlusOne(carByCarId);
        return  map;
    }

    /**
     * 查询的汽车Id的时候 查询量加1
     * @param unCar
     */
    public void hotCarPlusOne(UnCar unCar){

//        try {
//            redisTemplate.opsForZSet().add("car", unCar, 0);
//        } catch (Exception e) {
//            System.out.println("没有错误，加了个数据而已。");
//            e.printStackTrace();
//        }
        Double car = redisTemplate.opsForZSet().incrementScore("car", unCar, 1);
        System.out.println("car===>"+car);
    }

    @RequestMapping("/getAllHotCar")
    public Set getAllHotCar(){

        Set set = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("car", 0, POSITIVE_INFINITY, 0, 3);
        return set;
    }



    @PostMapping("/insertUncar")
    public String insertUnCar(@RequestBody UnCar unCar,HttpServletRequest request){

        User user = findUserByToken(request);
        System.out.println(user);
        redisTemplate.opsForHash().put("car_evaluateprice",user.getUserMail(),unCar);
        redisTemplate.expire("car_evaluateprice",7,TimeUnit.DAYS);

        redisTemplate.opsForHash().put("car_evaluateprice_user",user.getUserMail(),user);
        redisTemplate.expire("car_evaluateprice_user",7,TimeUnit.DAYS);


        return "success";

//        Integer car = unCarService.insertCar(unCar);
//
//        if(car>0){
//            return "success";
//        }
//
//        return "failed";
    }

    @PostMapping("/saveUnCar")
    public String saveUnCar(@RequestBody UnCar unCar){
        Integer integer = unCarService.insertCar(unCar);
        if(integer>0){
            return "success";
        }
        return "failed";
    }


    @GetMapping("/queryUncar")
    public Map<String,List> saveUnCar(){

        Map map = new HashMap();

        List car_evaluateprice = redisTemplate.opsForHash().values("car_evaluateprice");
        List car_evaluateprice_user = redisTemplate.opsForHash().values("car_evaluateprice_user");
        map.put("car",car_evaluateprice);
        map.put("user",car_evaluateprice_user);

        return map;


    }

    @GetMapping("/getBrand")
    public Map<String,Set<String>> getBrand(){
        Map<String, Set<String>> map = unCarService.getBrand();
        return map;
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
