## whut-web技术课程后端项目
### 配置说明
#### Druid
通过浏览器输入/durid访问
账号：admin 密码：123456
#### Swagger2
通过浏览器输入baseUrl/swagger-ui.html#/访问
### Bug日志
1. src/main/java下新建报名不能与项目名相同,也不能为某些java关键字,否则建立的包可能是简单的目录。
2. druid的stat-view-servlet中allow可以配置为空，但是不能不配置，否则部署到服务器会出现没有权限。
3. order表中表明是关键字，sql语句会出错，改为order_form
4. 自定义sql语句模糊查询
//    @Query(value = "select * from book where book_name like CONCAT('%',:bookName,'%')", nativeQuery = true)
//    List<Book> findBooksByBookNameLike(@Param("bookName") String bookName);
5. #{#Object.property} ?1/?2
6. username="13720113769";
   password="lihangfei";
7. order_form表中的payTime允许为空
