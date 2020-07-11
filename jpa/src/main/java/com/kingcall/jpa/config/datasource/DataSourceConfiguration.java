package com.kingcall.jpa.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 只有一个数据源的时候不用配置
 */
@Configuration
public class DataSourceConfiguration {

    @Bean("primaryDataSoure")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.datasource1")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }
}
