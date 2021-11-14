package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.dto.AlbumIn;
import com.sinkerflow.music.api.dto.AlbumOut;
import com.sinkerflow.music.dao.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    Album inToEntity(AlbumIn entity);

    AlbumOut entityToOut(Album entity);
}
