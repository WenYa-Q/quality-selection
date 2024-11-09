package com.wenya.quality.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "quality-selection.auth")
public class UserAuthProperties {

    private List<String> noAuthUrls;
}
