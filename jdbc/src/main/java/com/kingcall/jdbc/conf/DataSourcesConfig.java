package com.kingcall.jdbc.conf;


import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C)
 *
 * @program: boot
 * @description: 数据源
 * @author: 刘文强  kingcall
 * @create: 2018-09-20 07:41
 **/
@Configuration
public class DataSourcesConfig {
  /*  @Bean(name = "dataSource")
    public DataSource dataSource(Environment env){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.druid.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.druid.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.druid.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.druid.driver-class-name"));
        return dataSource;
    }*/
}
