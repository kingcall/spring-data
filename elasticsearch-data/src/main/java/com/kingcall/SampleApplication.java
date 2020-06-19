package com.kingcall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 这个对版本是有要求的，要求es 的版本是6.X
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty("initial-import.enabled")
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }

}
