package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//标注当前工程为eureka的服务端
@EnableEurekaServer
public class SecondBigCarEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondBigCarEurekaApplication.class);
    }
}
