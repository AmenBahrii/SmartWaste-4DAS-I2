package com.smartwaste.identityservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Response entity containing a hello greeting and service status")
public class HelloMessage {

    @Schema(description = "Greeting message", example = "Hello from SmartWaste Identity Service! Swagger is working properly.")
    private String message;

    @Schema(description = "Name of the microservice", example = "identity-service")
    private String service;

    @Schema(description = "Timestamp when the greeting was generated", example = "2026-11-13T12:00:00")
    private LocalDateTime timestamp;

    @Schema(description = "Status of the service", example = "UP")
    private String status;

    public HelloMessage() {
    }

    public HelloMessage(String message, String service, LocalDateTime timestamp, String status) {
        this.message = message;
        this.service = service;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
