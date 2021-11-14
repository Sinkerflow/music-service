package com.sinkerflow.music.api.dto;

import com.sinkerflow.music.dao.model.Audit;
import com.sinkerflow.music.dao.model.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MusicOut {

    private UUID id;

    private String name;

    private String url;

    private UUID artistId;

    private Collection<Source> source;

    private Audit audit;
}
