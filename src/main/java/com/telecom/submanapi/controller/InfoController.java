package com.telecom.submanapi.controller;

import com.telecom.submanapi.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InfoController {

    private final AppConfig appConfig;

    @Autowired
    public InfoController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/info")
    public Map<String, String> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("name", appConfig.getName());
        info.put("version", appConfig.getVersion());
        info.put("environment", appConfig.getEnvironment());
        info.put("status", "running");
        return info;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("environment", appConfig.getEnvironment());
        status.put("timestamp", java.time.LocalDateTime.now());
        status.put("uptime", "healthy");
        return status;
    }
}
