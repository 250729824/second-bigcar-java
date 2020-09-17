package com.qf.quarz;

import com.qf.dao.IUnCarDao;
import com.qf.pojo.UnCar;
import com.qf.service.IUnCarService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 54110 on 2020/9/16.
 */
@Component
public class TestQuarz {

    @Autowired
    IUnCarDao unCarDao;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("getAllCar")
    public ReturnT<String> demoJobHandler(String param) throws Exception {

        List<UnCar> cars = unCarDao.getAllCar();
        for (UnCar car:cars) {
            System.out.println(car);
            redisTemplate.opsForList().rightPush("carlist",car);
        }
        return ReturnT.SUCCESS;
    }


}
