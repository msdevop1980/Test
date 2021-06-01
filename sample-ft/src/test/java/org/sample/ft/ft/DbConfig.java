package org.sample.ft.ft;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db.test")
public class DbConfig {
    private String user;
    private String password;
    private String dbUrl;

}
