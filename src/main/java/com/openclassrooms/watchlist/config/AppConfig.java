package com.openclassrooms.watchlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:app-config.xml"})
public class AppConfig {
}