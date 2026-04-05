package com.taskflow.taskservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
        info = @Info(
                title = "University Tasks API",
                version = "1.0",
                description = "Microservice responsible for managing the workflow of tasks and deadlines"
        )
)
public class TaskServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(TaskServiceApplication.class, args);
    }
}
