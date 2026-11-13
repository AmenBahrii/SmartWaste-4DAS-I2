package com.smartwaste.identityservice.controller;

import com.smartwaste.identityservice.entity.HelloMessage;
import com.smartwaste.identityservice.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@Tag(name = "Hello API", description = "Endpoints for testing Swagger / OpenAPI integration and Identity Service connectivity")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @Operation(
            summary = "Get Hello greeting",
            description = "Returns a hello greeting message to test and verify that Swagger UI and Identity Service are functioning properly."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Greeting retrieved successfully")
    })
    public ResponseEntity<HelloMessage> getHello() {
        return ResponseEntity.ok(helloService.getHelloMessage());
    }

    @GetMapping("/personalized")
    @Operation(
            summary = "Get personalized Hello greeting",
            description = "Returns a personalized hello greeting message for the supplied name."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personalized greeting retrieved successfully")
    })
    public ResponseEntity<HelloMessage> getPersonalizedHello(
            @Parameter(description = "Name to include in the greeting", example = "Spanky")
            @RequestParam(name = "name", defaultValue = "Developer") String name
    ) {
        return ResponseEntity.ok(helloService.getPersonalizedHello(name));
    }
}
