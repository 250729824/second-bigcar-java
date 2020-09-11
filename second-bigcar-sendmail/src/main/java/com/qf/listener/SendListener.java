package com.qf.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String from;
    @RabbitListener(queues = "second-bigcar-email")
    public void sendMail(Map map){
        Integer i = (int)(Math.random() * (10000-1000+1) + 1000);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        Object email = map.get("email");
        simpleMailMessage.setTo(email.toString());
        simpleMailMessage.setSubject("注册的验证码");
        simpleMailMessage.setText(i.toString());
        try{
            javaMailSender.send(simpleMailMessage);
            redisTemplate.opsForValue().set(email.toString(),i.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
