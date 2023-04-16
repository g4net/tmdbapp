package com.g4net.tmdbapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
@PropertySource(value = {"classpath:application.properties", "classpath:application-secret.properties" })
public class AppConfig
{

    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}
}
