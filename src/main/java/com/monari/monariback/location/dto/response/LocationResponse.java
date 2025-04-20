package com.monari.monariback.location.dto.response;

import com.monari.monariback.location.entity.Location;
import java.time.LocalDateTime;

public record LocationResponse(
    Integer id,
    String serviceSubcategory,
    String serviceStatus,
    String paymentMethod,
    String locationName,
    String serviceUrl,
    LocalDateTime registrationStartDateTime,
    LocalDateTime registrationEndDateTime,
    LocalDateTime cancellationStartDateTime,
    LocalDateTime cancellationEndDateTime,
    String cancellationPolicyInfo,
    Integer cancellationDeadline,
    String x,
    String y
) {

    public static LocationResponse from(final Location location) {
        return new LocationResponse(
            location.getId(),
            location.getServiceSubcategory(),
            location.getServiceStatus(),
            location.getPaymentMethod(),
            location.getLocationName(),
            location.getServiceUrl(),
            location.getRegistrationStartDateTime(),
            location.getRegistrationEndDateTime(),
            location.getCancellationStartDateTime(),
            location.getCancellationEndDateTime(),
            location.getCancellationPolicyInfo(),
            location.getCancellationDeadline(),
            location.getX(),
            location.getY()
        );
    }
}
