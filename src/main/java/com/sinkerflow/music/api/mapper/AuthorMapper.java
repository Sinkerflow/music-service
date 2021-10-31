package com.sinkerflow.music.api.mapper;

import com.sinkerflow.music.api.dto.ArtistDto;
import com.sinkerflow.music.dao.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    ArtistDto entityToDto(Artist artist);

    Artist dtoToEntity(ArtistDto author);
}
