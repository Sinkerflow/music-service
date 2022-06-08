package com.sinkerflow;

import com.sinkerflow.api.model.AlbumIn;
import com.sinkerflow.api.model.AlbumOut;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@Disabled
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlbumIntegrationTest {

    @LocalServerPort
    private int port;

    private String url = "http://127.0.0.1";

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        url = url
                .concat(":")
                .concat(port + "")
                .concat("/v1")
                .concat("/albums");
    }

    @Test
    void whenBulkCreatedAlbums_thenWillBeCreatedSuccess() {
        List<AlbumIn> albums = Stream.generate(() -> AlbumIn.builder()
                        .name(RandomStringUtils.random(50))
                        .description(RandomStringUtils.random(255))
                        .artists(Collections.emptyList())
                        .build())
                .limit(6000)
                .collect(Collectors.toList());

        List<ResponseEntity<AlbumOut>> responses = albums.stream()
                .map(album -> restTemplate.postForEntity(url, album, AlbumOut.class))
                .collect(Collectors.toList());


        for (int i = 0; i < responses.size(); i++) {
            assertNotNull(responses.get(i));
            assertNotNull(responses.get(i).getBody());

            assertNotNull(responses.get(i).getBody().getUrl());

            assertNotNull(responses.get(i).getBody().getName());
            assertEquals(albums.get(i).getName(), responses.get(i).getBody().getName());

            assertNotNull(responses.get(i).getBody().getDescription());
            assertEquals(albums.get(i).getDescription(), responses.get(i).getBody().getDescription());
        }
    }
}
