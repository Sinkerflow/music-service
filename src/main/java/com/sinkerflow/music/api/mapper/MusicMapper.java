package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.model.MusicIn;
import com.sinkerflow.music.api.model.MusicOut;
import com.sinkerflow.music.dao.model.Music;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring")
@MapperConfig(uses = {MusicMapper.class})
public interface MusicMapper {

    @SuppressWarnings("unused")
    MusicMapper INSTANCE = Mappers.getMapper(MusicMapper.class);

    // Single
    Music inToEntity(MusicIn entity);

    MusicOut entityToOut(Music entity);

    // Many
    Collection<Music> inToEntity(Collection<MusicIn> entity);

    Collection<MusicOut> entityToOutList(Collection<Music> entity);
}
