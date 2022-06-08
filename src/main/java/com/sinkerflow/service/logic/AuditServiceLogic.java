package com.sinkerflow.service.logic;

import com.sinkerflow.dao.entity.AuditEntity;
import com.sinkerflow.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Slf4j
@Service
public class AuditServiceLogic implements AuditService {

    public AuditEntity update(AuditEntity audit) {
        if (Objects.isNull(audit)) {
            return new AuditEntity();
        }
        return AuditEntity.builder()
                .createdAt(audit.getCreatedAt())
                .updatedAt(Instant.now())
                .build();
    }
}
