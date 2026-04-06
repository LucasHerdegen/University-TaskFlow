package com.taskflow.auditservice.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "audit_logs")
@RequiredArgsConstructor
public class AuditLog
{
    @Id
    private String id;

    private final String message;

    private final LocalDate creationDate;
}
