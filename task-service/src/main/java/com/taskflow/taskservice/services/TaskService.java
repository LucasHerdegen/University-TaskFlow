package com.taskflow.taskservice.services;

import com.taskflow.taskservice.dtos.TaskDto;
import com.taskflow.taskservice.dtos.TaskPostDto;
import com.taskflow.taskservice.entities.Task;
import com.taskflow.taskservice.entities.TaskState;
import com.taskflow.taskservice.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService
{
    private final TaskRepository repository;

    private final RabbitTemplate rabbitTemplate;

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
        rabbitTemplate.convertAndSend("task.created.queue", "Se creo una nueva tarea: " + task.getTitle());

        return new TaskDto(task.getId(), task.getTitle(), task.getSubject(), task.getExpirationDate(), task.getState());
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void markOverdueTasks()
    {
        System.out.println("Running task cleanup...");

        var overdueTasks = repository.findByStateAndExpirationDateBefore(TaskState.OVERDUE, LocalDate.now());

        System.out.println("Cleaning " + overdueTasks.size() + " tasks");

        overdueTasks.forEach(t -> t.setState(TaskState.OVERDUE));
        repository.saveAll(overdueTasks);

        System.out.println("Cleaning finished");
    }
}
