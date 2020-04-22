package com.rzdp.fortresseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FortressEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortressEurekaServerApplication.class, args);
    }

}
