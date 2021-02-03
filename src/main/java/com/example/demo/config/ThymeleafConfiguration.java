package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

    @Autowired
    private ThymeleafProperties thymeleafProperties;

    @Value("${spring.thymeleaf.web.prefix:http://localhost:3000/}")
    private String webSuffix;

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5 from http")
    public ITemplateResolver webTemplateResolver() {
        UrlTemplateResolver urltemplateResolver = new UrlTemplateResolver();
        urltemplateResolver.setPrefix(webSuffix);
        urltemplateResolver.setSuffix(thymeleafProperties.getSuffix());
        urltemplateResolver.setOrder(1);
        urltemplateResolver.setCacheable(thymeleafProperties.isCache());
        urltemplateResolver.setCharacterEncoding(thymeleafProperties.getEncoding().name());
        return urltemplateResolver;
    }

}
