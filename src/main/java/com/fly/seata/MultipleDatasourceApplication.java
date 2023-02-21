package com.fly.seata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: peijiepang
 * @date 2021/4/15
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.fly.seata.dao")
public class MultipleDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceApplication.class);
    }

}
