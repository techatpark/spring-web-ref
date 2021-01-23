package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

    @Value("${spring.thymeleaf.suffix:.html}")
    private String suffix;

    @Value("${spring.thymeleaf.cache:false}")
    private Boolean cacheable;

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5 from http")
    public ITemplateResolver webTemplateResolver() {
        UrlTemplateResolver urltemplateResolver = new UrlTemplateResolver();
        urltemplateResolver.setPrefix("http://localhost:3002/");
        urltemplateResolver.setSuffix(suffix);
        urltemplateResolver.setOrder(1);
        urltemplateResolver.setCacheable(cacheable);
        return urltemplateResolver;
    }

}
