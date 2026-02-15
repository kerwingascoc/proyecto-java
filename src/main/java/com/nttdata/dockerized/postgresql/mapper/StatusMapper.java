package com.nttdata.dockerized.postgresql.mapper;

import org.mapstruct.Named;

public class StatusMapper {

    @Named("stringToBoolean")
    public static Boolean stringToBoolean(String status) {
        if (status==null) return null;

        return switch( status ) {
            case "Active" -> true;
            case "Inactive" -> false;
            default -> null;
        };
    }

    @Named("booleanToString")
    public static String booleanToString(Boolean active) {
        return active == null ? null : ( active ? "Active":"Inactive");
    }
}
