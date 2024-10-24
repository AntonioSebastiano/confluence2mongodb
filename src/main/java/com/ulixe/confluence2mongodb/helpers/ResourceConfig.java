package com.ulixe.confluence2mongodb.helpers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "input.file")
public class ResourceConfig {

    private String path;  // Il path che viene letto da application.yml

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
