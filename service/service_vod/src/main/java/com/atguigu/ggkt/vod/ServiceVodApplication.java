package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Missile
 * @Date 2022-06-29-10:12
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")//为了扫描其他模块里的配置类
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
