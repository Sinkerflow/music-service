package com.sinkerflow.music.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@Data
@Document
public class Music {

    private UUID id;

    private String url;

    private String name;

    private Artist artist;

    private Source source;
}
