package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        // Opción 1: Usar puerto alternativo si 8080 está ocupado
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setDefaultProperties(java.util.Collections.singletonMap("server.port", "8081"));
        app.run(args);
        
        // O Opción 2: Detectar puerto libre automáticamente
        // SpringApplication.run(DemoApplication.class, args);
    }
}