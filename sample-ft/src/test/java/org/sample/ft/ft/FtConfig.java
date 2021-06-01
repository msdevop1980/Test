package org.sample.ft.ft;


import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Builder
@Getter
@Configuration
@ConfigurationProperties("ft")
public class FtConfig {


    private String endPoint;
    private String restPath;



}