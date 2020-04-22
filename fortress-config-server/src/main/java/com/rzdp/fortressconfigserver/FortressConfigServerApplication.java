package com.rzdp.fortressconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FortressConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortressConfigServerApplication.class, args);
    }

}
