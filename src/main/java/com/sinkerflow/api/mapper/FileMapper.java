package com.sinkerflow.api.mapper;

import com.sinkerflow.api.model.ArtistOut;
import com.sinkerflow.api.model.FileOut;
import com.sinkerflow.dao.entity.ArtistEntity;
import com.sinkerflow.dao.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileOut entityToOut(FileEntity entity);
}
