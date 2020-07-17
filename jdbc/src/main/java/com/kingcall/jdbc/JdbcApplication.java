package com.kingcall.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class JdbcApplication implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnections();
    }

    private void showConnections(){
        try {
            System.out.println( dataSource.toString());
            System.out.println( dataSource.getConnection().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
    }
}
