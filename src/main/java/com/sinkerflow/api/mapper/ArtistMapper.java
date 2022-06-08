package com.sinkerflow.api.mapper;

import com.sinkerflow.api.model.ArtistIn;
import com.sinkerflow.api.model.ArtistOut;
import com.sinkerflow.dao.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    // @Mapping(target = "albums", expression = "java(albumIdsToEntities(artist))")
    ArtistEntity inToEntity(ArtistIn artist);

    ArtistOut entityToOut(ArtistEntity entity);

    List<ArtistEntity> inToEntityList(List<ArtistIn> artists);

    List<ArtistOut> entityToOutList(List<ArtistEntity> entities);

    ArtistEntity entityToEntity(ArtistEntity entities);

    List<ArtistEntity> entityToEntityList(List<ArtistEntity> entities);
}
