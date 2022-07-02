package com.works.restcontrollers;

import com.works.useProfile.IConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ProfileRestController {

    final IConfig config;
    public ProfileRestController(IConfig config) {
        this.config = config;
    }

    @GetMapping("/profile")
    public Map profile() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("config", config.data());
        return hm;
    }

}
