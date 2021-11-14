package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.dto.ArtistIn;
import com.sinkerflow.music.api.dto.ArtistOut;
import com.sinkerflow.music.dao.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    Artist inToEntity(ArtistIn entity);

    ArtistOut entityToOut(Artist entity);
}
