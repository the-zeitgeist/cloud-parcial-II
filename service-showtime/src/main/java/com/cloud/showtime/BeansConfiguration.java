package com.cloud.showtime;

import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public MyService myService(){
        return new MyService();
    }

    @Bean
    public ResponseBuilder responseBuilder(){
        return new ResponseBuilder();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
