package com.taskflow.taskservice.dtos;

import com.taskflow.taskservice.entities.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TaskPostDto
{
    @Schema(description = "Task title")
    private String title;

    @Schema(description = "Subject task")
    private String subject;

    @Schema(description = "Deadline for submitting a task", example = "2026-11-20")
    private LocalDate expirationDate;

    @Schema(description = "Task state", example = "PENDING")
    private TaskState state;
}
