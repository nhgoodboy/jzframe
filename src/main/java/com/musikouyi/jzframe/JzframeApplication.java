package com.musikouyi.jzframe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class JzframeApplication {

    private static final Logger logger = LoggerFactory.getLogger(JzframeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JzframeApplication.class, args);
        logger.info("JzframeApplication启动成功");
    }
}
