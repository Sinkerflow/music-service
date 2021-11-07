package com.sinkerflow.music.service.logic;

import com.sinkerflow.music.dao.model.Audit;
import com.sinkerflow.music.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Slf4j
@Service
public class AuditServiceLogic implements AuditService {

    public Audit update(Audit audit) {
        if (Objects.isNull(audit))
            return new Audit();
        if (Objects.isNull(audit.getCreatedAt()))
            audit.setCreatedAt(Instant.now());
        audit.setUpdatedAt(Instant.now());
        return audit;
    }
}
