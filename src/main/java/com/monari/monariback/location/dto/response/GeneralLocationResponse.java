package com.monari.monariback.location.dto.response;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.location.entity.GeneralLocation;

public record GeneralLocationResponse(
    Integer id,
    String locationName,
    String x,
    String y,
    Region region,
    String serviceUrl

) {

    public static GeneralLocationResponse from(final GeneralLocation generalLocation) {
        return new GeneralLocationResponse(
            generalLocation.getId(),
            generalLocation.getLocationName(),
            generalLocation.getX(),
            generalLocation.getY(),
            generalLocation.getRegion(),
            generalLocation.getServiceUrl()
        );
    }
}
