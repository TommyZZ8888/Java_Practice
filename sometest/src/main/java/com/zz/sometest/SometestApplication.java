package com.zz.sometest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootApplication
public class SometestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SometestApplication.class, args);
    }

}
