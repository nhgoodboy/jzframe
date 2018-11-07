package com.musikouyi.jzframe;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.musikouyi.jzframe.dao.repository")  //解决Jpa扫描到Elasticsearch的Repositories的问题
//@EnableElasticsearchRepositories(basePackages = "com.musikouyi.jzframe.dao.search")
@MapperScan(basePackages = "com.musikouyi.jzframe.dao.mapper")
@EnableCaching
public class JzframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JzframeApplication.class, args);
        log.info("JzframeApplication启动成功");
    }
}
