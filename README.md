# JAVASE🥇

深入浅出的SpringAOP

1.AOP的作用

　　在OOP中，正是这种分散在各处且与对象核心功能无关的代码（横切代码）的存在，使得模块复用难度增加。AOP则将封装好的对象剖开，找出其中对多个对象产生影响的公共行为，并将其封装为一个可重用的模块，这个模块被命名为“切面”（Aspect），切面将那些与业务无关，却被业务模块共同调用的逻辑提取并封装起来，减少了系统中的重复代码，降低了模块间的耦合度，同时提高了系统的可维护性。

2.DI 和 IOC 概念

　　依赖注入或控制反转的定义中，调用者不负责被调用者的实例创建工作，该工作由Spring框架中的容器来负责，它通过开发者的配置来判断实例类型，创建后再注入调用者。由于Spring容器负责被调用者实例，实例创建后又负责将该实例注入调用者，因此称为依赖注入。而被调用者的实例创建工作不再由调用者来创建而是由Spring来创建，控制权由应用代码转移到了外部容器，控制权发生了反转，因此称为控制反转。

3.BeanFactory与ApplicationContext

　　ApplicationContext是BeanFactory的子接口，也被称为应用上下文。BeanFactory提供了Spring的配置框架和基本功能，ApplicationContext则添加了更多企业级功能（如国际化的支持），他另一重要优势在于当ApplicationContext容器初始化完成后，容器中所有的 singleton Bean 也都被实例化了，也就是说当你需要使用singleton Bean 是，在应用中无需等待就可以用，而其他BeanFactory接口的实现类，则会延迟到调用 getBean（）方法时构造，ApplicationContext的初始化时间会稍长些，调用getBean（）是由于Bean已经构造完毕，速度会更快。因此大部分系统都使用ApplicationContext，而只在资源较少的情况下，才考虑使用BeanFactory。

4.AOP的实现策略

（1）Java SE动态代理：
    使用动态代理可以为一个或多个接口在运行期动态生成实现对象，生成的对象中实现接口的方法时可以添加增强代码，从而实现AOP。缺点是只能针对接口进行代理，另外由于动态代理是通过反射实现的，有时可能要考虑反射调用的开销。
（2）字节码生成（CGLib 动态代理）
    动态字节码生成技术是指在运行时动态生成指定类的一个子类对象，并覆盖其中特定方法，覆盖方法时可以添加增强代码，从而实现AOP。其常用工具是cglib。
（3）定制的类加载器
    当需要对类的所有对象都添加增强，动态代理和字节码生成本质上都需要动态构造代理对象，即最终被增强的对象是由AOP框架生成，不是开发者new出来的。解决的办法就是实现自定义的类加载器，在一个类被加载时对其进行增强。JBoss就是采用这种方式实现AOP功能。
（4）代码生成
    利用工具在已有代码基础上生成新的代码，其中可以添加任何横切代码来实现AOP。
（5）语言扩展
    可以对构造方法和属性的赋值操作进行增强，AspectJ是采用这种方式实现AOP的一个常见Java语言扩展。

 

注意：AOP中的切面封装了增强（Advice）和切点（Pointcut），下面先开始只使用增强，切点暂且不加入。




