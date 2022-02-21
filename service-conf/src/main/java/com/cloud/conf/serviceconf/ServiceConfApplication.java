package com.cloud.conf.serviceconf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ServiceConfApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConfApplication.class, args);
    }

}
