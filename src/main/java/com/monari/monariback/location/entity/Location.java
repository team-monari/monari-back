package com.monari.monariback.location.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_subcategory")
    private String serviceSubcategory;

    @Column(name = "service_status")
    private String serviceStatus;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "service_url")
    private String serviceUrl;

    @Column(name = "registration_start_date_time")
    private LocalDateTime registrationStartDateTime;

    @Column(name = "registration_end_date_time")
    private LocalDateTime registrationEndDateTime;

    @Column(name = "cancellation_start_date_time")
    private LocalDateTime cancellationStartDateTime;

    @Column(name = "cancellation_end_date_time")
    private LocalDateTime cancellationEndDateTime;

    @Column(name = "cancellation_policy_info")
    private String cancellationPolicyInfo;

    @Column(name = "cancellation_deadline")
    private Integer cancellationDeadline;

    @Column(name = "x")
    private String x;

    @Column(name = "y")
    private String y;


    public static Location ofCreate(
        final String serviceSubcategory,
        final String serviceStatus,
        final String paymentMethod,
        final String locationName,
        final String serviceUrl,
        final String registrationStartDateTime,
        final String registrationEndDateTime,
        final String cancellationStartDateTime,
        final String cancellationEndDateTime,
        final String cancellationPolicyInfo,
        final String cancellationDeadline,
        final String x,
        final String y
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        return new Location(
            null,
            nullIfBlank(serviceSubcategory),
            nullIfBlank(serviceStatus),
            nullIfBlank(paymentMethod),
            nullIfBlank(locationName),
            nullIfBlank(serviceUrl),
            parseDate(registrationStartDateTime, formatter),
            parseDate(registrationEndDateTime, formatter),
            parseDate(cancellationStartDateTime, formatter),
            parseDate(cancellationEndDateTime, formatter),
            nullIfBlank(cancellationPolicyInfo),
            parseInteger(cancellationDeadline),
            nullIfBlank(x),
            nullIfBlank(y)
        );
    }

    private static String nullIfBlank(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    private static LocalDateTime parseDate(String value, DateTimeFormatter formatter) {
        String v = nullIfBlank(value);
        return v == null ? null : LocalDateTime.parse(v, formatter);
    }

    private static Integer parseInteger(String value) {
        String v = nullIfBlank(value);
        return v == null ? null : Integer.parseInt(v);
    }
}