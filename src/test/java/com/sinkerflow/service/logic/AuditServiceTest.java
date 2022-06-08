package com.sinkerflow.service.logic;

import com.sinkerflow.ApplicationTest;
import com.sinkerflow.dao.entity.AuditEntity;
import com.sinkerflow.service.AuditService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AuditServiceTest extends ApplicationTest {

    @Autowired
    AuditService auditService;

    @Test
    void whenAuditIsNull_thenWillBeCreatedNewAuditObject() {
        AuditEntity audit = null;

        AuditEntity updatedAudit = auditService.update(null);

        assertNotNull(updatedAudit);
        assertNotNull(updatedAudit.getCreatedAt());
        assertNotNull(updatedAudit.getUpdatedAt());
    }

    @Test
    void whenAuditCreated_thenCreatedAtAndUpdatedAtAreEquals() {
        AuditEntity audit = new AuditEntity();

        assertEquals(audit.getCreatedAt(), audit.getUpdatedAt());
    }

    @Test
    void whenUpdateAudit_thenUpdatedWithSuccess() {
        AuditEntity audit = new AuditEntity();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        AuditEntity updatedAudit = auditService.update(audit);

        assertEquals(updatedAudit.getCreatedAt(), audit.getCreatedAt());
        assertNotEquals(updatedAudit.getUpdatedAt().toString(), audit.getUpdatedAt().toString());
    }
}
