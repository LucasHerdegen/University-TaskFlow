package com.taskflow.lightclient.dtos;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TaskDto
{
    private Long id;

    private String title;

    private String subject;

    private LocalDate expirationDate;

    private TaskState state;
}
