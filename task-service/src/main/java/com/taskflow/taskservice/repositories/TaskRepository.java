package com.taskflow.taskservice.repositories;

import com.taskflow.taskservice.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>
{
}
