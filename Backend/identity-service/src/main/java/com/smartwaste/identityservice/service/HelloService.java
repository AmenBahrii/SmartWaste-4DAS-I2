package com.smartwaste.identityservice.service;

import com.smartwaste.identityservice.entity.HelloMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

    private static final String SERVICE_NAME = "identity-service";

    public HelloMessage getHelloMessage() {
        return new HelloMessage(
                "Hello from SmartWaste Identity Service! Swagger is working properly.",
                SERVICE_NAME,
                LocalDateTime.now(),
                "UP"
        );
    }

    public HelloMessage getPersonalizedHello(String name) {
        String greeting = (name == null || name.trim().isEmpty())
                ? "Hello from SmartWaste Identity Service! Swagger is working properly."
                : String.format("Hello, %s! Swagger is working properly in Identity Service.", name.trim());

        return new HelloMessage(
                greeting,
                SERVICE_NAME,
                LocalDateTime.now(),
                "UP"
        );
    }
}
