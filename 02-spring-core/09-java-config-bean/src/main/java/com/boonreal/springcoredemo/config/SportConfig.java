package com.boonreal.springcoredemo.config;

import com.boonreal.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    public SwimCoach swimCoach(){
        return new SwimCoach();
    }
}
