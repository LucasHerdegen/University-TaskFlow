package com.taskflow.taskservice.services;

import com.taskflow.taskservice.dtos.TaskDto;
import com.taskflow.taskservice.dtos.TaskPostDto;
import com.taskflow.taskservice.entities.Task;
import com.taskflow.taskservice.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService
{
    private final TaskRepository repository;

    public List<TaskDto> getTask()
    {
        var tasks = repository.findAll();

        return tasks.stream().map(t ->
                new TaskDto(t.getId(), t.getTitle(), t.getSubject(), t.getExpirationDate(), t.getState())
        ).toList();
    }

    public TaskDto getTask(Long id)
    {
        var task = repository.findById(id).orElse(null);

        if (task == null)
            return null;

        return new TaskDto(task.getId(), task.getTitle(), task.getSubject(), task.getExpirationDate(), task.getState());
    }

    public TaskDto createTask(TaskPostDto dto)
    {
        if (dto.getTitle().isEmpty() || dto.getSubject().isEmpty() || dto.getExpirationDate().isBefore(LocalDate.now()))
            return null;

        var task = new Task(dto.getTitle(), dto.getSubject(), dto.getExpirationDate(), dto.getState());

        repository.save(task);

        return new TaskDto(task.getId(), task.getTitle(), task.getSubject(), task.getExpirationDate(), task.getState());
    }
}
