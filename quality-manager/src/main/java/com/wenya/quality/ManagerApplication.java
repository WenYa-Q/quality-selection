package com.wenya.quality;

import com.wenya.quality.log.annotation.EnableSysLogAspect;
import com.wenya.quality.properties.UserAuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableSysLogAspect
@EnableAsync
@MapperScan("com.wenya.quality.mapper")
@EnableConfigurationProperties(value = {UserAuthProperties.class})
@Slf4j
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
