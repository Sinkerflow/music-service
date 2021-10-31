package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.dto.MusicDto;
import com.sinkerflow.music.dao.model.Music;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@MapperConfig(uses = {
        AuthorMapper.class
})
public interface MusicMapper {

    MusicMapper INSTANCE = Mappers.getMapper(MusicMapper.class);

    MusicDto entityToDto(Music music);

    Music dtoToEntity(MusicDto music);
}
