package com.sinkerflow.music.dao.model;

import com.sinkerflow.music.dao.model.type.FormatType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Source {

    private String name;

    private FormatType type;

    private String link;
}
