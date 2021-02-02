package com.example.demo.config;

import com.example.demo.controller.converter.PaymentStatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PaymentStatusConverter());
    }
}
