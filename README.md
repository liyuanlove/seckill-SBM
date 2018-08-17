# 秒杀

## 开发环境
```
IntelliJ IDEA: 2017.3
Java version: 1.8.0_152
Apache Maven: 3.5.2
Your MySQL Server version: 5.7.20 MySQL Community Server (GPL)
SpringBoot: 2.0.3.RELEASE
Apache Shiro: 1.4.0
Beetl: 2.8.5
Redis server v=3.2.100
```


## 运行
	>- 打包好的jar，下载后配置好JDK和数据库(mysql和redis),双击运行。
	>- 浏览器输入：http://localhost:8080/seckill/list ,即可访问。

## 项目结构
```
├─java
│  └─org.tan.seckill
│     │  SeckillApplication.java  启动类，程序入口
│     ├─config  配置
│     ├─mapper  dao层
│     ├─core  aop,dto,enum,redis等
│     ├─po  实体类
│     ├─service
│     └─web  控制层
└─resources
    │  application.yml  SpringBoot配置文件
    │  logback-spring.xml  放到该目录下，SpringBoot会自动扫描
    ├─static                                 //静态资源
    └─templates
```
## 项目导入(IDEA)
- Import Project，找到解压后的Source Code
- Import project from external model -> Maven -> next...

## 联系我
- Email:  tobeanengineer@foxmail.com
