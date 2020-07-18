package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class DruidDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.toString());
        while (true){
            TimeUnit.SECONDS.sleep(1);
            dataSource.getConnection().createStatement().execute("select count(1) from user");
        }
    }
}
