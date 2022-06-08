package com.sinkerflow.api;

import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.api.handler.exception.SinkerException;
import com.sinkerflow.api.mapper.FileMapper;
import com.sinkerflow.api.model.FileOut;
import com.sinkerflow.api.model.type.FileType;
import com.sinkerflow.dao.entity.FileEntity;
import com.sinkerflow.dao.repository.FileRepository;
import com.sinkerflow.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import static com.sinkerflow.api.handler.BusinessCode.ALBUM_1000;

@Slf4j
@RestController
@RequestMapping("/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;
    private final FileService fileService;
    private final FileMapper fileMapper;

    @PostMapping
    public FileOut upload(@RequestParam MultipartFile files, @RequestParam FileType type, @RequestParam UUID parentId) {
        return fileMapper.entityToOut(fileService.upload(files, type, parentId));
    }

    @Transactional
    @GetMapping("/{id}")
    public void download(@PathVariable(name = "id") UUID id, HttpServletResponse response) {
        FileEntity record = fileRepository.findById(id)
                .orElseThrow(() -> new SinkerException(Entry.of(ALBUM_1000)));

        response.addHeader("Content-Disposition", "attachment; filename=" + record.getName());

        try {
            IOUtils.copy(record.getData().getBinaryStream(), response.getOutputStream());
        } catch (IOException | SQLException e) {
            log.error("{}", e.getMessage());
            throw new SinkerException(Entry.of(ALBUM_1000));
        }
        log.info("Sent file id: {}", id);
    }
}
