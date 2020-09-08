package com.qf.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name="redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        //使用fastjson实现对象的序列化
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        //设置“值”的序列化
        redisTemplate.setValueSerializer(serializer);
        //设置“hash”类型的数据的序列化方式
        redisTemplate.setHashKeySerializer(serializer);
        //设置“key”的数据的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置“hash”的“key”的数据的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置redisTemplate对象的工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    /**
     * 注入StringRedisTemplate对象
     * @param redisConnectionFactory
     * @return
     */

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        //设置StringRedisTemplate的连接工厂
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);

        return stringRedisTemplate;
    }




}
