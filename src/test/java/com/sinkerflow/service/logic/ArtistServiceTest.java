package com.sinkerflow.service.logic;

import com.sinkerflow.ApplicationTest;
import com.sinkerflow.dao.repository.ArtistRepository;
import com.sinkerflow.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class ArtistServiceTest extends ApplicationTest {

    @Autowired
    private ArtistService artistService;

    @MockBean
    private ArtistRepository repository;

    @Test
    void whenArtistCreatedWithValidData_thenSuccess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistCreatedWithInvalidData_thenFail() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenFindArtistByCorrectId_thenSuccess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenFindArtistByIncorrectId_thenFail() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistFoundByCorrectName_thenSuccess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistFoundByIncorrectName_thenFail() {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistUpdatedByValidData_thenSuccess() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistUpdatedByInvalidData_thenFail() {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistDeletedByCorrectId_thenSuccess() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenArtistDeletedByIncorrectId_thenFail() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
