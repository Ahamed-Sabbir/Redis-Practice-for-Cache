package com.sabbir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPracticeApplication.class, args);
    }

}
