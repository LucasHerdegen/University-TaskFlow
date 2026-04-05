package com.taskflow.taskservice.repositories;

import com.taskflow.taskservice.entities.Task;
import com.taskflow.taskservice.entities.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    List<Task> findByStateAndExpirationDateBefore(TaskState state, LocalDate date);
}
