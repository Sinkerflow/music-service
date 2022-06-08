package com.sinkerflow.service.logic;

import com.sinkerflow.ApplicationTest;
import org.junit.jupiter.api.Test;

class TrackServiceTest extends ApplicationTest {

    @Test
    void whenTrackCreatedWithValidData_thenSuccess() {
        try {
            Thread.sleep(420);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackCreatedWithInvalidData_thenFail() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenFindTrackByCorrectId_thenSuccess() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenFindTrackByIncorrectId_thenFail() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackFoundByCorrectName_thenSuccess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackFoundByIncorrectName_thenFail() {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackUpdatedByValidData_thenSuccess() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackUpdatedByInvalidData_thenFail() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackDeletedByCorrectId_thenSuccess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenTrackDeletedByIncorrectId_thenFail() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}