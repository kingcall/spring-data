package com.kingcall.jpa.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 可以看做是将数据源  dao 和 实体类进行对应的过程
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.kingcall.jpa.dao.user",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    @Qualifier("primaryDataSoure")
    private DataSource primaryDataSoure;

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @return
     */
    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public EntityManagerFactory primaryEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.kingcall.jpa.entity.user");
        factory.setDataSource(primaryDataSoure);//数据源

        factory.setJpaPropertyMap(jpaProperties.getProperties());
        factory.afterPropertiesSet();//在完成了其它所有相关的配置加载以及属性设置后,才初始化
        return factory.getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(this.primaryEntityManagerFactory());
        return jpaTransactionManager;
    }
}
