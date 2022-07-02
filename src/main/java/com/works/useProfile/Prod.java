package com.works.useProfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile("prod")
@PropertySource("classpath:application.properties")
public class Prod implements IConfig{

    @Value("${site.name}")
    private String name;

    @Override
    public Map<EConfig, Object> data() {
        Map<EConfig, Object> hm = new HashMap<>();
        hm.put(EConfig.apiKey, "prod122534523n534h534h");
        hm.put(EConfig.username, "prod01" );
        hm.put(EConfig.password, "54321");
        hm.put(EConfig.url, "http://prod.site.com");
        hm.put(EConfig.title, name);
        return hm;
    }

}