package com.musikouyi.jzframe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
//@ServletComponentScan
public class JzframeApplication {


    public static void main(String[] args) {
        SpringApplication.run(JzframeApplication.class, args);
        log.info("JzframeApplication启动成功");
    }
}
