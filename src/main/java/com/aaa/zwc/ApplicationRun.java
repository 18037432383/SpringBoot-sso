package com.aaa.zwc;/**
 * @Date: 2020-05-09 21:12
 * @Author: 秀仔
 * @Description
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * user : 秀仔
 * Data: 2020/5/9
 */
@SpringBootApplication
@MapperScan("com.aaa.zwc.mapper")
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);
    }
}
