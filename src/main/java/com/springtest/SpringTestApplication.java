package com.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.springtest.SpringTestApplication.class, args);
    }

}
