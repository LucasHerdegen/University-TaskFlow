package com.taskflow.taskservice.dtos;

import com.taskflow.taskservice.entities.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TaskDto
{
    @Schema(description = "Unique task identifier")
    private Long id;

    @Schema(description = "Task title")
    private String title;

    @Schema(description = "Task subject")
    private String subject;

    @Schema(description = "Deadline for submitting a task", example = "2026-11-20")
    private LocalDate expirationDate;

    @Schema(description = "Task state", example = "PENDING")
    private TaskState state;
}
