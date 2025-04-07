package com.monari.monariback.location.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.api")
public class LocationApiProperties {

    private String key;
    private String serviceName;
    private String dataType;
    private String baseUrl;
}
