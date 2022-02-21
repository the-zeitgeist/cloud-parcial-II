package com.cloud.showtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ShowtimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowtimeApplication.class, args);
    }

}
