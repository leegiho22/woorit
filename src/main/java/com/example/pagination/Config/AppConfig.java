package com.example.pagination.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ModelMapper 빈을 추가한다.
//빈을 추가하면 private final ModelMapper modelMapper; 선언하여 자동 주입이 가능하고
//빈을 추가하지 않으면 private final ModelMapper modelMapper = new ModelMapper();
//선언하여 사용한다.
@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
