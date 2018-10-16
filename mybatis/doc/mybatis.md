# mybatis 
- ## 基本原理
    - mapper 对象和 jpa 的dao 有点像
- ## 使用方法  
    - xml 文件版
    - 注解版  
      - 使用方法
           - 在对应的 接口上加 @Mapper注解，在有很多接口的时候每个接口多需要很麻烦
           - @MapperScan(basePackages = {"com.kingcall.mybatis.mapper"}) 可以在启动类上加需要扫描的包，会自动给包下的所有接口加上 @Mapper注解
       - 使用简单
- ## 注意问题
    -   mybatis中数据库不能自动识别大写字母，如userName，它只认识user_name，因此需要转换
        - 注解版
            - 使用下面的方式
            - 实现一个接口对象
        - xml 版
            - 通过全局配置文件直接解决
    ```text
        @Select("SELECT * FROM gardenprice")
        @Results({
                @Result(column = "user_name",property = "userName")
        })
        List<GardenPrice> findAll();

    ```