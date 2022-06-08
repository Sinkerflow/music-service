package com.sinkerflow.service.logic;

import com.sinkerflow.api.handler.BusinessCode;
import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.api.handler.exception.SinkerException;
import com.sinkerflow.api.model.type.FileType;
import com.sinkerflow.dao.entity.FileEntity;
import com.sinkerflow.dao.repository.AlbumRepository;
import com.sinkerflow.dao.repository.FileRepository;
import com.sinkerflow.service.FileService;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.UUID;

@AllArgsConstructor
@Service
public class FileServiceLogic implements FileService {

    private final FileRepository fileRepository;
    private final SessionFactory sessionFactory;
    private final AlbumRepository albumRepository;

    @Transactional
    @Override
    public FileEntity upload(MultipartFile file, FileType type, UUID parentId) {
        FileEntity result;
        try {
            result = fileRepository.save(FileEntity.builder()
                    .id(UUID.randomUUID())
                    .name(file.getOriginalFilename())
                    .type(type)
                    .parentId(parentId)
                    .data(createBlog(file.getInputStream(), file.getSize()))
                    .build());

            if (type == FileType.ALBUM) {
                var album = albumRepository.findById(parentId)
                        .orElseThrow(() -> new SinkerException(Entry.of(BusinessCode.ALBUM_1000))); // todo: fix this business code
                album.setCoverId(result.getId());
            }
        } catch (IOException e) {
            throw new SinkerException(Entry.of(BusinessCode.ALBUM_1000)); // todo: fix this business code
        }
        return result;
    }

    private Blob createBlog(InputStream content, long size) {
        return sessionFactory.openSession().getLobHelper().createBlob(content, size);
    }

    @Override
    public FileEntity download(UUID fileId) {
        return null;
    }
}
