package com.sinkerflow.service;

import com.sinkerflow.api.model.type.FileType;
import com.sinkerflow.dao.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {

    FileEntity upload(MultipartFile files, FileType type, UUID parentId);

    FileEntity download(UUID fileId);
}
