package com.taskflow.taskservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Setter
@Getter
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subject;

    private LocalDate expirationDate;

    @Enumerated(value = EnumType.STRING)
    private TaskState state;

    public Task(String title, String subject, LocalDate expirationDate, TaskState state)
    {
        this.title = title;
        this.subject = subject;
        this.expirationDate = expirationDate;
        this.state = state;
    }
}
