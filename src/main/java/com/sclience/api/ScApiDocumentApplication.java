package com.sclience.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class ScApiDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScApiDocumentApplication.class, args);
    }

}
