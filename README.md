# cas-base
对cas进行系统的学习。

### cas-server 
- 目前完成的功能：配置密码jdbc、自定义密码认证、自定义登录验证、服务端集成shiro权限认证
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

 
 
