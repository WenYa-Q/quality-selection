package com.wenya.quality.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * knife4j配置
 *
 * @author wenya
 */
@Configuration
public class Knife4jConfig {

    /**
     * 分组开放api
     *
     * @return {@link GroupedOpenApi }
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags()!=null){
                openApi.getTags().forEach(tag -> {
                    Map<String,Object> map=new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0,100));
                    tag.setExtensions(map);
                });
            }
            if(openApi.getPaths()!=null){
                openApi.addExtension("x-test123","333");
                openApi.getPaths().addExtension("x-abb",RandomUtil.randomInt(1,100));
            }

        };
    }

    /**
     * 自定义开放api
     *
     * @return {@link OpenAPI }
     */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
                new Info().title("尚品甄选")
                        .version("1.0")
                        .description("尚品甄选api文档")
                        .contact(new Contact().name("wenya").url("http://wenya.com"))
        );
    }
}
