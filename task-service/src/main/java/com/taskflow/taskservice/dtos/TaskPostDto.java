package com.taskflow.taskservice.dtos;

import com.taskflow.taskservice.entities.TaskState;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TaskPostDto
{
    private String title;

    private String subject;

    private LocalDate expirationDate;

    private TaskState state;
}
