# lockiely-parent
基于Spring Boot 快速开发的手脚架，集成及扩展了Mybatis-plus、多数据源自动切换、集成了Shiro认证授权，使用Redis进行Session缓存共享。

## V0.1.0 版本： 主要功能如下：
1.1. 基于Spring Boot 快速开发的手脚架
1.2. 完全Restful的api形式，前后端分离，此项目只有后端
1.3. 基于Mybatis-plus 上进行了功能性的扩展，实现了基础的CRUD功能及分页，实现了乐观锁、逻辑删除等功能
1.4. 多数据源的基于注解自动切换，一主多备，读写分离，同时可根据策略进行数据源的选择，做到负载均衡
1.5. 使用Shiro进行认证授权，Redis作为Session共享的缓存，shiro 的CacheManager 也使用Redis作为缓存。
1.6. 使用Redis管理登录匹配人员的信息，解决密码错误情况下进行访问。
1.7. 项目中使用了统一属性配置，大量可配置化参数可进行重新配置。

### 欢迎广大朋友共同学习讨论 ： 
