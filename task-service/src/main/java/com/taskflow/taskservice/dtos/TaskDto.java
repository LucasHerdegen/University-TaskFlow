package com.taskflow.taskservice.dtos;

import com.taskflow.taskservice.entities.TaskState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TaskDto
{
    private Long id;

    private String title;

    private String subject;

    private LocalDate expirationDate;

    private TaskState state;
}
