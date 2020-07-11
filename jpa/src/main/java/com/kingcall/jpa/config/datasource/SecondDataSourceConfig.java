package com.kingcall.jpa.config.datasource;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 可以看做是将数据源  dao 和 实体类进行对应的过程
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.kingcall.jpa.dao.product",
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager"
)
public class SecondDataSourceConfig {

    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    @Qualifier("secondDatasource")
    private DataSource secondDatasource;



    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @return
     */
    @Bean(name = "secondEntityManagerFactory")
    public EntityManagerFactory secondEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.kingcall.jpa.entity.product");
        factory.setDataSource(secondDatasource);//数据源

        factory.setJpaPropertyMap(jpaProperties.getProperties());
        factory.afterPropertiesSet();//在完成了其它所有相关的配置加载以及属性设置后,才初始化
        return factory.getObject();
    }



    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(this.secondEntityManagerFactory());
        return jpaTransactionManager;
    }
}
