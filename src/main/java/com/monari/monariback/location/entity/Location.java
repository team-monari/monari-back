package com.monari.monariback.location.entity;

import com.monari.monariback.location.dto.LocationDto;
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

    public static Location ofCreate(LocationDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        return new Location(
            null,
            nullIfBlank(dto.minClassNm()),
            nullIfBlank(dto.svcStatNm()),
            nullIfBlank(dto.payAtNm()),
            nullIfBlank(dto.placeNm()),
            nullIfBlank(dto.svcUrl()),
            parseDate(dto.svcOpnBgnDt(), formatter),
            parseDate(dto.svcOpnEndDt(), formatter),
            parseDate(dto.rcptBgnDt(), formatter),
            parseDate(dto.rcptEndDt(), formatter),
            nullIfBlank(dto.revStdDayNm()),
            parseInteger(dto.revStdDay())
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