package edu.wtbu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"edu.wtbu.dao","classpath:mapper"})

public class SunshineAirlinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunshineAirlinesApplication.class, args);
    }

}
