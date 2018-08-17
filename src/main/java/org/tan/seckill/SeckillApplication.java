package org.tan.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mapper接口(注意这里是tk包下的)
@MapperScan(basePackages = "org.tan.seckill.mapper")
@EnableTransactionManagement
@EnableCaching
//开启定时任务
//@EnableScheduling
//开启异步执行程序
//@EnableAsync
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages = {"org.tan.seckill"})
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
