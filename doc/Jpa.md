 -  # Jpa 操作数据库
    - ## 理论概念  
         - Jpa 对象持久化的概念与实现      
         - Hibernate3.0 对 jpa 规范的实现           
         - SpringData 本身是对各种数据源采用Jpa()进行操作的的集合 
         - Spring Data JPA 是Spring Data 的一个子项目，它通过提供基于JPA的Repository极大了减少了操作JPA的代码
    - ## 实际操作
        - 核心概念（三个接口）
        ~~~puml
        @startuml
          用户自定义接口 -> JpaRepository: 提供了对整个数据库的操作
          JpaRepository -> PagingAndSortingRepository:提供了分页查询
          PagingAndSortingRepository->CrudRepository: 提供了增删改查以及批量操作的接口
        @enduml
        ~~~
       - 说明
            - 默认请款下 @Entity 标注的class类的小写就是表名，否则要手动指定
            - 不用每个属性都用@column 标注，默认所有属性都是字段
       - 基于接口的数据库操作 
           ~~~text
           当用户继承 JpaRepository 之后，就会有大量的数据库操作的api(方法可以使用)
           接口默认的查询抽象方法都是基于主键，至少这个是不满足需求的。
           ~~~~
           - 对于添加记录操作，如果不存在则添加，否则更新
           - 删除操作，如果不存在特定的ID则报错，根据记录本身删除就不要使用了
       - 基于方法名的查询
           ~~~text
             当我们继承了 JpaRepository 接口后，我们还可以写大量的自定义的抽象方法，来操作数据库，而这些方法的名字
             、参数是有要求的,其中有大量的关键字可以供我们使用！
           ~~~
           ~~~java
           public interface UserReposity extends JpaRepository<User,Integer> {
               List<User> findUserByNameOrderByIdDesc(String name);
               List<User> findUserByNameOrderByIdAsc(String name);
               List<User> findByName(String name);
               List<User> findFirst3ByName(String name);
               List<User> findByNameAndId(String name,Integer id);
               List<User> findByNameOrId(String name,Integer id);
               List<User> findDistinctByName(String name);
               List<User> findDistinctUserByName(String name);       
           }
           ~~~
       - @query 的查询方式
           - @query 注解允许在方法上使用sql,即使用sql 进行查询
           - sql(sql、jpql)
               - sql：标准sql  
                   - 在原生的sql语句中，count聚合函数的结果类型为BigInteger
               - jpql:HQL
                   - hibernate 使用的是hql语句而不是sql语句
                   - 因为是HQL 所以我们查询的时候写的不是表名，而是对象类的名称（User）
           - 都支持命名参数
           - 示例 
           ~~~text
               @Query(value = "select * from user where name=?1",nativeQuery = true)
               public List<User> findUsers(String name);
               @Query(value = "select u from User u where u.name=?1")
               public List<User> findUsers2(String name);
               @Query(value = "select * from user  where name=:name and id=:id",nativeQuery = true)
               public List<User> findUsers3(@Param("name") String name, @Param("id") Integer id);
               @Query(value = "select * from user  where name=?1 and id=?2",nativeQuery = true)
               public List<User> findUsers4(String name,Integer id);
               @Query(value = "select department_id,count(*) as cnt from user group by department_id",nativeQuery = true)
               public List<Object[]> departmentCount();
           ~~~
       - Example 查询
           - 接口自定义的抽象方法、根据方法名(自定义的抽象方法)，sql(标准sql和jpql:HQL) 最终会生成 Jpql 语句
           - Example 查询允许构建一个 Example对象，spring data 根据 Example对象生成 Jpql 语句
           - Example 对象的参数中的所有属性会采取 And 的方式去构建 Jpql
           - 支持ExampleMatcher 对象（如筛选的时候忽略大小写）
           - 示例
           ~~~text
               @Test
               public void findAll(){
                   User user = new User();
                   user.setDepartmentId(4);
                   user.setName("kingcall");
                   Example<User> example = Example.of(user);
                   List<User> users = userService.findAll(example);
                   users.forEach(x-> System.out.println(x));
               }
               Hibernate: select user0_.id as id1_1_, user0_.create_time as create_t2_1_, user0_.department_id as departme3_1_, user0_.name as name4_1_ from user user0_ where user0_.name=? and user0_.department_id=4
           ~~~
       - @NamedQuery查询
          - 相关信息需要写在实体类上，而不是接口里了。
       - Jpa query 查询