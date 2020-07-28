package com.kingcall.mybatisdynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kingcall.mybatisdynamic.mapper")
public class MybatisDynamicsqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDynamicsqlApplication.class, args);
    }

}
