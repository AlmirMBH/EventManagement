package com.zekerijah.eventdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Event", version = "2.0", description = "Event application"))
public class EventdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventdemoApplication.class, args);
    }

}
