package com.rzdp.fortressbankservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FortressBankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortressBankServiceApplication.class, args);
    }

}
