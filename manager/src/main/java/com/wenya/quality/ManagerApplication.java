package com.wenya.quality;

import com.wenya.quality.log.annotation.EnableSysLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSysLogAspect
@MapperScan("com.wenya.quality")
@Slf4j
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
