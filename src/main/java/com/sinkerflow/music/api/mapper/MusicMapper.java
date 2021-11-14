package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.dto.MusicIn;
import com.sinkerflow.music.api.dto.MusicOut;
import com.sinkerflow.music.dao.model.Music;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@MapperConfig(uses = {
        MusicMapper.class
})
public interface MusicMapper {

    MusicMapper INSTANCE = Mappers.getMapper(MusicMapper.class);

    Music inToEntity(MusicIn entity);

    MusicOut entityToOut(Music entity);
}
