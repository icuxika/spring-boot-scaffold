package com.icuxika.scaffold.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:framework.properties")
public class Framework {

    @Value("#{'${secureRequestPaths}'.split(',')}")
    private List<String> secureRequestPaths;

    public List<String> getSecureRequestPaths() {
        return secureRequestPaths;
    }
}
