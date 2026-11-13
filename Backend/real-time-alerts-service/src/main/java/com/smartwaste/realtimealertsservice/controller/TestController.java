package com.smartwaste.realtimealertsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test Controller", description = "Connectivity and health test endpoints")
public class TestController {

    @GetMapping("/hello")
    @Operation(summary = "Test connectivity", description = "Returns a greeting to verify service connectivity and Swagger")
    public String hello() {
        return "Hello World from Real-Time Alerts Service!";
    }
}
