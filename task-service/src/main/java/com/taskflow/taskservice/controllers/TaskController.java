package com.taskflow.taskservice.controllers;

import com.taskflow.taskservice.dtos.TaskDto;
import com.taskflow.taskservice.dtos.TaskPostDto;
import com.taskflow.taskservice.services.TaskService;
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
public class TaskController
{
    private final TaskService service;

    @GetMapping
    public List<TaskDto> getTasks()
    {
        return service.getTask();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id)
    {
        var task = service.getTask(id);

        if (task == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

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
