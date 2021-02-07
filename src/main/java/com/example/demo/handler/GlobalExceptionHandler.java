package com.example.demo.handler;

import com.example.demo.model.AppError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;

@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {


    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        AppError appError = new AppError();
        appError.setCode("Code");
        appError.setDescription("Des");

        if (throwable instanceof AccessDeniedException) {



        } else {

        }

        if (serverWebExchange.getResponse().isCommitted()) {
            return Mono.error(throwable);
        }

        serverWebExchange.getResponse().setStatusCode(status);
        return serverWebExchange
                .getResponse()
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
                    try {
                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(appError));
                    } catch (Exception ex) {
                        return bufferFactory.wrap(new byte[0]);
                    }
                }));
    }
}
