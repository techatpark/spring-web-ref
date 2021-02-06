package com.example.demo.config;

import com.example.demo.resolver.TenantResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class WebConfiguration implements WebFluxConfigurer {

    private final TenantResolver tenantResolver;

    public WebConfiguration(TenantResolver tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(tenantResolver);


    }
}
