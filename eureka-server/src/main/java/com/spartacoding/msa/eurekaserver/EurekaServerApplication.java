package com.spartacoding.msa.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication { // TODO Eureka 설정

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
