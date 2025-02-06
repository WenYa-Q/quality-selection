package com.wenya;

import com.wenya.quality.anno.EnableUserWebMvcConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务用户应用程序
 * Description：
 *
 * @author wuqiulin
 */
@SpringBootApplication
@Slf4j
@EnableUserWebMvcConfiguration
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}