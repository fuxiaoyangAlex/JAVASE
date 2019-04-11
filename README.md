# TheFirstLibrary
#MyBatis的核心配置

 - 7.1 MyBatis的核心对象：
        在使用MyBatis框架时，主要涉及两个核心对象：SqlSessionFactory和SqlSession,它们在MyBatis框架中起着至关重要的作用。
- 7.1.1 *SqlSessionFactory*
    SqlSessionFactory时MyBatis框架中十分重要的作用，它是单个数据库映射关系经过编译后的内存镜像，其主要作用是创建SqlSession。SqlSessionFactoryBuilder则可以通过XML配置文件。通过XML配置文件构建出SqlSessionFactory实例,其实现代码如下
>//读取配置文件
InputStream inputStream = Resources.getResourceAsStream("配置文件位置")；
//根据配置文件构建SqlSessionFactory
SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);

- 7.1.2 *SqlSession*
   SqlSession是MyBatis框架中的另一个重要的对象，它是应用程序与持久层之间执行交互操作的一个单线程对象，其主要作用是执行持久化操作。SqlSession对象包含了数据库中所有执行SQL操作的方法，由于其底层封装了JDBC连接，所以可以直接使用其实例来执行已映射的SQL语句。
> SqlSession sqlSession = sqlSessionFactory.openSession();
try{
    //此处执行持久化操作
}finally{
    sqlSession.close();
}

  **SqlSession对象包含了很多方法，其常用方法如下所示：**
> < T > T selectOne(String statement);
    查询方法。参数statement是在配置文件中定义的< select >元
素的id。使用该方法后，会返回执行SQL语句查询结果的一条泛型对象。<BR>
 < T >T selectOne(String statement, Object parameter);
    查询方法。参数statement是在配置文件中定义的< select >元素的Id,parameter是查询所需要的参数。<BR>
< E > List<E> selectList(String statement);
    查询方法。参数statement是在配置文件中定义的< select  >元素的id，使用该方法后，会放回执行SQL语句查询结果的泛型对象的集合。<BR> 
< E >  List<E> selectList(String statement,Object parameter);
    查询方法。参数statement是在配置文件中定义的< select >


---
 - 7.2配置文件

---
  - 7.3映射文件

