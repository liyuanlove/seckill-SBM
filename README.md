# 秒杀

## 开发环境
- IntelliJ IDEA 2017 2.5
- Maven 3.3.9
- JDK 1.8.0_141
- MariaDB 10.2.8
- Redis 4.0.1

## 项目技术
- SpringBoot 1.5.7
- Thymeleaf 
- Mybatis
- Druid 1.1.3
- log4j2
- Jedis 2.9.0

## 项目资源
- 源码:  [Seckill-master.zip](https://github.com/redinw/Seckill/archive/master.zip)
- 打包:  [seckill-0.0.1-SNAPSHOT.jar](https://github.com/redinw/Seckill/releases/download/v0.0.1/seckill-0.0.1-SNAPSHOT.jar)
	>- 打包好的jar，下载后配置好JDK和数据库(mysql和redis),双击运行。
	>- 浏览器输入：http://localhost:8080/seckill/list ,即可访问。

## 项目结构
```
├─java
│  └─org.redin.seckill
│     │  SeckillApplication.java            //启动类，程序入口
│     ├─config
│     │      Beans.java
│     ├─dao
│     │  │  SeckillMapper.java
│     │  │  SuccessKilledMapper.java
│     │  └─cache
│     │          RedisDao.java
│     ├─dto
│     │      Exposer.java
│     │      SeckillExecution.java
│     ├─enums
│     │      SeckillStateEnum.java
│     ├─exception
│     │      RepeatKillException.java        //重复秒杀
│     │      SeckillClosedException.java     //秒杀结束
│     │      SeckillException.java           
│     ├─po
│     │      Seckill.java
│     │      SuccessKilled.java
│     ├─service
│     │  │  ISeckillService.java
│     │  │  
│     │  └─impl
│     │          SeckillServiceImpl.java
│     ├─vo
│     │      SeckillResult.java
│     └─web
│            SeckillController.java
└─resources
    │  application.properties                //SpringBoot配置文件
    │  log4j2.xml                            //log4j2配置文件，放到该目录下，SpringBoot会自动扫描
    ├─mapper
    │      SeckillMapper.xml
    │      SuccessKilledMapper.xml
    ├─sql
    │      procedure.sql                     //存储过程
    │      schema.sql                        //建库建表
    ├─static                                 //静态资源
    └─templates
            detail.html                      //秒杀详情页
            list.html                        //秒杀列表
```
## 项目导入(IDEA)
- Import Project，找到解压后的Source Code
- Import project from external model -> Maven -> next...

## 资料
- [Spring-Boot干货系列](http://tengj.top/categories/springMVC%E5%B9%B2%E8%B4%A7%E7%B3%BB%E5%88%97/)
- [Druid Spring Boot Starter](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter)
- [新一代Java模板引擎Thymeleaf](https://www.tianmaying.com/tutorial/using-thymeleaf)

## 联系我
- Email:  ycoder@outlook.com
