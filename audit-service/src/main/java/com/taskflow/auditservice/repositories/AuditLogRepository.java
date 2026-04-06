package com.taskflow.auditservice.repositories;

import com.taskflow.auditservice.entities.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLog, String>
{
}
