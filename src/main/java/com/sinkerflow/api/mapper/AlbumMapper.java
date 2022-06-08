package com.sinkerflow.api.mapper;

import com.sinkerflow.api.model.AlbumIn;
import com.sinkerflow.api.model.AlbumOut;
import com.sinkerflow.dao.entity.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    AlbumEntity inToEntity(AlbumIn entity);

    AlbumOut entityToOut(AlbumEntity entity);

    List<AlbumOut> entitiesToOut(List<AlbumEntity> entity);
}
