package com.wenya.quality.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 用户安全属性
 * Description：
 *
 * @author wuqiulin
 */
@Data
@ConfigurationProperties(prefix = "quality-selection.auth")
public class UserAuthProperties {

    private List<String> noAuthUrls;
}
