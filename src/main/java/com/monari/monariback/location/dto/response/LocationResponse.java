package com.monari.monariback.location.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monari.monariback.location.dto.LocationDto;
import java.util.List;

public record LocationResponse(
    @JsonProperty("ListPublicReservationInstitution")
    ServiceData listPublicReservationInstitution
) {

    public record ServiceData(
        List<LocationDto> row
    ) {

    }
}