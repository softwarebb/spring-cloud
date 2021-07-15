package com.example.tmy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class EurekaClientConsumerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumerRestApplication.class, args);
    }

}
