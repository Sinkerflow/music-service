package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.model.ArtistIn;
import com.sinkerflow.music.api.model.ArtistOut;
import com.sinkerflow.music.dao.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    Artist inToEntity(ArtistIn entity);

    ArtistOut entityToOut(Artist entity);

    Collection<Artist> inToEntityList(Collection<ArtistIn> entity);

    Collection<ArtistOut> entityToOutList(Collection<Artist> entity);
}
