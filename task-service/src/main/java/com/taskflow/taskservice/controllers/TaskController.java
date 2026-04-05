package com.taskflow.taskservice.controllers;

import com.taskflow.taskservice.dtos.TaskDto;
import com.taskflow.taskservice.dtos.TaskPostDto;
import com.taskflow.taskservice.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "Endpoints to create and viewing tasks")
public class TaskController
{
    private final TaskService service;

    @Operation(summary = "It queries all Tasks")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public List<TaskDto> getTasks()
    {
        return service.getTask();
    }

    @Operation(summary = "It finds a Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "200", description = "Task found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id)
    {
        var task = service.getTask(id);

        if (task == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

    @Operation(summary = "It creates a new Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "204", description = "Task created")
    })
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskPostDto dto)
    {
        var task = service.createTask(dto);

        if (task == null)
            return ResponseEntity.badRequest().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getId())
                .toUri();

        return ResponseEntity.created(location).body(task);
    }
}
