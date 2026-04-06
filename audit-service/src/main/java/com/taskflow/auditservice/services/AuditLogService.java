package com.taskflow.auditservice.services;

import com.taskflow.auditservice.entities.AuditLog;
import com.taskflow.auditservice.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuditLogService
{
    private final AuditLogRepository repository;

    @RabbitListener(queues = "task.created.queue")
    public void manageTaskCreation(String message)
    {
        var auditLog = new AuditLog(message, LocalDate.now());
        repository.save(auditLog);
    }
}
