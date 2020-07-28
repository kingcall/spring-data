package com.kingcall.mybatis2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kingcall.mybatis2.mapper")
public class Mybatis2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatis2Application.class, args);
    }

}
