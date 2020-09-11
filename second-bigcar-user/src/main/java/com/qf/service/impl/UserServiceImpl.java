package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.UserDao;
import com.qf.pojo.BaseResult;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String loginUser(User user) {
        String userMail = user.getUserMail();
        Object o = redisTemplate.opsForValue().get(userMail);
        if(o!=null){
            String code = user.getCode();
            if(o.toString().equals(code)){
                UUID token = UUID.randomUUID();
                //以token为key，查询出的用户对象为value存储搭配redis中
                redisTemplate.opsForValue().set(token.toString(),user);
                redisTemplate.expire(token.toString(),30, TimeUnit.MINUTES);
                return token.toString();
            }else{
                return "验证码错误";
            }
        }
        return "验证码超时";
    }

    @Override
    public String findByUserMail(String usermail) {

        User byUserMail = userDao.findByUserMail(usermail);

        Map map = new HashMap();
        User user = new User();
        user.setUserMail(usermail);
        if (byUserMail == null) {
            User save = userDao.save(user);
            if (save == null) {
                return "邮箱首次登录失败";
            }
            map.put("email", save.getUserMail());
            rabbitTemplate.convertAndSend("second-bigcar-email", map);
            return "success";
        }
        map.put("email", user.getUserMail());
        rabbitTemplate.convertAndSend("second-bigcar-email", map);
        return "success";
    }

    @Override
    public BaseResult findUser(HttpServletRequest request) {
        User user = findUserByToken(request);
        if(redisTemplate.hasKey("cars_"+user.getCode())){
            List list = redisTemplate.opsForHash().values("cars_" + user.getCode());
            BaseResult baseResult = new BaseResult();
            baseResult.setList(list);
            return baseResult;
        }

        return null;
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
