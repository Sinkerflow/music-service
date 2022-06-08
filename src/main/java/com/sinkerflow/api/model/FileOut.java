package com.sinkerflow.api.model;

import com.sinkerflow.api.model.type.FileType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class FileOut {

    private UUID id;

    private String name;

    private FileType type;

    private UUID parentId;
}
