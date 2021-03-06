# cas-base
对cas进行系统的学习。

### cas-server 
- 目前完成的功能：配置密码jdbc、自定义密码认证、自定义登录验证、服务端集成shiro权限认证、单点退出服务端操作
>> shrio认证：服务端只做角色验证，拥有当前角色则认证通过允许登录，没有则认证失败不允许登录
- overlay生成：overlay可以把多个项目war合并成为一个项目，并且如果项目存在同名文件，那么主项目中的文件将覆盖掉其他项目的同名文件。使用maven 的Overlay配置实现无侵入的改造cas
- 配置密码jdbc：基于官方文档使用配置properties的形式进行密码管理, 以下为部分配置
    ```
    application.properties
     #普通校验
     # 添加jdbc认证
     cas.authn.jdbc.query[0].sql = SELECT * FROM u_user WHERE email = ?
     #密码字段
     cas.authn.jdbc.query[0].fieldPassword=pswd
     #配置数据库连接
     cas.authn.jdbc.query[0].url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false
     cas.authn.jdbc.query[0].dialect=org.hibernate.dialect.MySQLDialect
     #数据库用户名
     cas.authn.jdbc.query[0].user=root
     #数据库密码
     cas.authn.jdbc.query[0].password=root
     #mysql驱动
     cas.authn.jdbc.query[0].driverClass=com.mysql.jdbc.Driver
     #配置加密策略 
      cas.authn.jdbc.query[0].passwordEncoder.type=DEFAULT
      cas.authn.jdbc.query[0].passwordEncoder.characterEncoding=UTF-8
      cas.authn.jdbc.query[0].passwordEncoder.encodingAlgorithm=MD5
    #md5加盐校验
    #加密迭代次数
    cas.authn.jdbc.encode[0].numberOfIterations=2
    #该列名的值可替代上面的值，但对密码加密时必须取该值进行处理
    cas.authn.jdbc.encode[0].numberOfIterationsFieldName=
    # 盐值固定列
    cas.authn.jdbc.encode[0].saltFieldName=email
    # 静态盐值
    cas.authn.jdbc.encode[0].staticSalt=.
    cas.authn.jdbc.encode[0].sql=SELECT * FROM u_user WHERE email = ?
    # 对处理盐值后的算法
    cas.authn.jdbc.encode[0].algorithmName=MD5
    cas.authn.jdbc.encode[0].passwordFieldName=pswd
    cas.authn.jdbc.encode[0].expiredFieldName=expired
    cas.authn.jdbc.encode[0].disabledFieldName=disabled
    #数据库连接
    cas.authn.jdbc.encode[0].url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false
    cas.authn.jdbc.encode[0].dialect=org.hibernate.dialect.MySQL5Dialect
    cas.authn.jdbc.encode[0].driverClass=com.mysql.jdbc.Driver
    cas.authn.jdbc.encode[0].user=root
    cas.authn.jdbc.encode[0].password=root
    #注册客户端
    cas.serviceRegistry.initFromJson=true
    cas.serviceRegistry.watcherEnabled=true
    cas.serviceRegistry.schedule.repeatInterval=120000
    cas.serviceRegistry.schedule.startDelay=15000
    cas.serviceRegistry.managementType=DEFAULT
    cas.serviceRegistry.json.location=classpath:/services
    
    #服务端配置单点登出
    #配置允许登出后跳转到指定页面
    cas.logout.followServiceRedirects=true
    # 跳转到指定页面需要的参数名为
    cas.logout.redirectParameter=service
    # 在退出时是否需要 确认一下  true确认 false直接退出
    cas.logout.confirmLogout=true
    # 是否移除子系统的票据
    cas.logout.removeDescendantTickets=true
    ```
 - 自定义密码认证
   - 从数据库获取信息，并且对密码进行MD5加密 或者 加盐方式处理.假如某些特殊情况下,密码规则不符合以上,我们就需要自定义密码校验。
 - 自定义登录验证
   - 我们在使用SSO单点登录的时候不只是验证一下用户名和密码是否一致,有时候还需要验证一些别的校验,那么这一张讲一下如何自定义验证器。 
   - 自定义验证类,主要实现AbstractUsernamePasswordAuthenticationHandler的authenticateUsernamePasswordInternal方法。
- 服务端集成shiro权限认证
  - 单点登录（SSO），只当企业用户同时访问多个不同（类型的）应用时，他们只需要提供自身的用户凭证信息（比如用户名/密码）一次，当用户在不同的应用间切换时，他们不用再重复地输入自身的用户凭证了。我的设计思路是SSO只做认证中心,各应用的授权在各自的服务做,比如 查看订单权限, 这个权限,它可能仅仅只是订单系统这个应用的权限。因此，授权应该在客户端做，本篇只是简单的介绍cas服务端与shiro 的集成, 只验证是否拥有角色,有角色就可以登录,没角色不可以登录。
### cas-client
- 目前实现功能：基于官方项目搭建
- 主要修改web.xml和服务端注册客户端
### spring-boot-Starter
- 可将cas-client的spring部分封装到一个starter中达到开箱即用的目的
- 主要需要注意：这里的启动装配方式有两种
  - 启动类装配：在META-INF中创建文件spring.factories，添加：
    ```               
    org.springframework.boot.autoconfigure.EnableAutoConfiguration= \
    配置conf
    ```
>> 给@EnableAutoConfiguration去自动扫描
  - 注解方式
    - 添加注解类
      ```
      /**
       * 配置注解在入口类添加EnableCasClient即可使用该Start
       */
      @Target(ElementType.TYPE)
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      @Inherited
      @Import(CasClientConfiguration.class)
      public @interface EnableCasClient {
      }
      ```
>> 而后只需在项目中添加注解 EnableCasClient 即可

### cas-spring-client
- 目前完成的功能有
  - 客户端集成
  - 自定义鉴权：客户端整合cas之后,无论我们访问什么地址,只要没有发现票据,都会跳转到cas服务端去进行登录。有时候我们有这样的需求,用户不登录也可以访问某些网页,这个时候就需要用到AuthenticationFilter的忽略地址功能。
  - cas单点退出

### cas-rest-support
- 提供登录验证接口
### cas-rest-server 
- 用于无法直接连接数据库，通过rest接口来验证登录
