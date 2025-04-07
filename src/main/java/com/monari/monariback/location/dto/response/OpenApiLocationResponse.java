package com.monari.monariback.location.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monari.monariback.location.dto.OpenApiLocationDto;
import java.util.List;

public record OpenApiLocationResponse(
    @JsonProperty("ListPublicReservationInstitution")
    ServiceData listPublicReservationInstitution
) {

    public record ServiceData(
        List<OpenApiLocationDto> row
    ) {

    }
}