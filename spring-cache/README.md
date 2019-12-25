1. 本例子是 SpringBoot1.4.7 + Ehcache2.x 集成的缓存；

2. 步骤

- pom.xml的依赖引入；
- 启动类添加 @EnableCaching
- 添加Ehcahce2.x 的ehcahce.xml配置文件
- 在application.propertites中配置缓存