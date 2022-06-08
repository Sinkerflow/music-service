package com.sinkerflow.api.mapper;

import com.sinkerflow.api.model.TrackIn;
import com.sinkerflow.api.model.TrackOut;
import com.sinkerflow.dao.entity.TrackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
@MapperConfig(uses = {TrackMapper.class})
public interface TrackMapper {

    @SuppressWarnings("unused")
    TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class);

    // Single
    TrackEntity inToEntity(TrackIn entity);

    TrackOut entityToOut(TrackEntity entity);

    // Many
    List<TrackEntity> inToEntity(List<TrackIn> entity);

    List<TrackOut> entityToOutList(List<TrackEntity> entity);
}
