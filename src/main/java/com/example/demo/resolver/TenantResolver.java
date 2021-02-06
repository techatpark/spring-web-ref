package com.example.demo.resolver;

import com.example.demo.model.Tenant;
import com.example.demo.service.TenantService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TenantResolver implements HandlerMethodArgumentResolver {

    private final TenantService tenantService;

    public TenantResolver(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Tenant.class);
    }

    @Override
    public Mono resolveArgument(MethodParameter methodParameter
            , BindingContext bindingContext
            , ServerWebExchange serverWebExchange) {
        String tenantCode = serverWebExchange.getRequest().getPath().value().replaceAll("/","");
        return tenantService.findByCode(tenantCode);
    }
}
