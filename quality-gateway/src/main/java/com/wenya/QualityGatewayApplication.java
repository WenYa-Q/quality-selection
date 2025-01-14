package com.wenya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关应用程序
 * Description：
 *
 * @author wuqiulin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QualityGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(QualityGatewayApplication.class, args);
    }
}