package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5 from http")
    public ITemplateResolver webTemplateResolver() {
        UrlTemplateResolver urltemplateResolver = new UrlTemplateResolver();
        urltemplateResolver.setPrefix("http://localhost:3000/");
        urltemplateResolver.setSuffix(".html");
        urltemplateResolver.setOrder(1);
        return urltemplateResolver;
    }

}
