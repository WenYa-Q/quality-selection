package com.wenya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 购物车应用程序
 * Description：
 *
 * @author wuqiulin
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServiceCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCartApplication.class, args);
    }
}
