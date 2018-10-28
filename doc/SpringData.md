# Spring Data
## 理论概念  
   - Jpa 对象持久化的概念与实现
   - Hibernate3.0 对 jpa 规范的实现
   - SpringData 本身是对各种数据源采用Jpa()进行操作的的集合 
       - Redis
       - 
## 数据库
   - ### mysql 数据库
        - JdbcTemplate 和 数据源（数据库连接池）之间的关系
        ~~~java
        // 所以这是我们创建多数据源的基础
        public class JdbcTemplate{
         	/**
         	 * Construct a new JdbcTemplate for bean usage.
         	 * <p>Note: The DataSource has to be set before using the instance.
         	 */
            public JdbcTemplate() {      
             
             }   
           	/**
           	 * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
           	 * <p>Note: This will not trigger initialization of the exception translator.
           	 * @param dataSource the JDBC DataSource to obtain connections from
           	 */
           	public JdbcTemplate(DataSource dataSource) {
           		setDataSource(dataSource);
           		afterPropertiesSet();
           	}
      	
           	/**
           	 * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
           	 * <p>Note: Depending on the "lazyInit" flag, initialization of the exception translator
           	 * will be triggered.
           	 * @param dataSource the JDBC DataSource to obtain connections from
           	 * @param lazyInit whether to lazily initialize the SQLExceptionTranslator
           	 */
           	public JdbcTemplate(DataSource dataSource, boolean lazyInit) {
           		setDataSource(dataSource);
           		setLazyInit(lazyInit);
           		afterPropertiesSet();
           	}
        }
       
        ~~~
        - JdbcTemplate  
            - queryForObject 两种形式，带参数绑定和不带参数绑定
        - Mybatis
       
## 参考
   [深入实践SpringBoot](https://github.com/chenfromsz)