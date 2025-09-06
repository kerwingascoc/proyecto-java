package com.nttdata.dockerized.postgresql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface BooleanMapper {
    @Named("stringToBoolean")
    default Boolean stringToBoolean(String active) {
        return "Active".equalsIgnoreCase(active);
    }
}
