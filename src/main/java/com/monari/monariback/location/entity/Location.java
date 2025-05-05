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

    @Column(name = "service_id", unique = true)
    private String serviceId;

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

    @Column(name = "areaNm")
    private String areaNm;


    public static Location ofCreate(
        final String serviceId,
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
        final String y,
        final String areaNm
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        return new Location(
            null,
            nullIfBlank(serviceId),
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
            nullIfBlank(y),
                nullIfBlank(areaNm)
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

    public boolean updateIfChanged(
        String serviceSubcategory,
        String serviceStatus,
        String paymentMethod,
        String locationName,
        String serviceUrl,
        String registrationStartDateTime,
        String registrationEndDateTime,
        String cancellationStartDateTime,
        String cancellationEndDateTime,
        String cancellationPolicyInfo,
        String cancellationDeadline,
        String x,
        String y,
        String areaNm
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        boolean isChanged = false;

        // String 필드 비교
        String newServiceSubcategory = nullIfBlank(serviceSubcategory);
        if (this.serviceSubcategory == null && newServiceSubcategory != null ||
            this.serviceSubcategory != null && !this.serviceSubcategory.equals(
                newServiceSubcategory)) {
            this.serviceSubcategory = newServiceSubcategory;
            isChanged = true;
        }

        String newServiceStatus = nullIfBlank(serviceStatus);
        if (this.serviceStatus == null && newServiceStatus != null ||
            this.serviceStatus != null && !this.serviceStatus.equals(newServiceStatus)) {
            this.serviceStatus = newServiceStatus;
            isChanged = true;
        }

        String newPaymentMethod = nullIfBlank(paymentMethod);
        if (this.paymentMethod == null && newPaymentMethod != null ||
            this.paymentMethod != null && !this.paymentMethod.equals(newPaymentMethod)) {
            this.paymentMethod = newPaymentMethod;
            isChanged = true;
        }

        String newLocationName = nullIfBlank(locationName);
        if (this.locationName == null && newLocationName != null ||
            this.locationName != null && !this.locationName.equals(newLocationName)) {
            this.locationName = newLocationName;
            isChanged = true;
        }

        String newServiceUrl = nullIfBlank(serviceUrl);
        if (this.serviceUrl == null && newServiceUrl != null ||
            this.serviceUrl != null && !this.serviceUrl.equals(newServiceUrl)) {
            this.serviceUrl = newServiceUrl;
            isChanged = true;
        }

        // LocalDateTime 필드 비교
        LocalDateTime newRegistrationStart = parseDate(registrationStartDateTime, formatter);
        if (this.registrationStartDateTime == null && newRegistrationStart != null ||
            this.registrationStartDateTime != null && !this.registrationStartDateTime.equals(
                newRegistrationStart)) {
            this.registrationStartDateTime = newRegistrationStart;
            isChanged = true;
        }

        LocalDateTime newRegistrationEnd = parseDate(registrationEndDateTime, formatter);
        if (this.registrationEndDateTime == null && newRegistrationEnd != null ||
            this.registrationEndDateTime != null && !this.registrationEndDateTime.equals(
                newRegistrationEnd)) {
            this.registrationEndDateTime = newRegistrationEnd;
            isChanged = true;
        }

        LocalDateTime newCancellationStart = parseDate(cancellationStartDateTime, formatter);
        if (this.cancellationStartDateTime == null && newCancellationStart != null ||
            this.cancellationStartDateTime != null && !this.cancellationStartDateTime.equals(
                newCancellationStart)) {
            this.cancellationStartDateTime = newCancellationStart;
            isChanged = true;
        }

        LocalDateTime newCancellationEnd = parseDate(cancellationEndDateTime, formatter);
        if (this.cancellationEndDateTime == null && newCancellationEnd != null ||
            this.cancellationEndDateTime != null && !this.cancellationEndDateTime.equals(
                newCancellationEnd)) {
            this.cancellationEndDateTime = newCancellationEnd;
            isChanged = true;
        }

        // cancellationPolicyInfo 비교
        String newCancellationPolicyInfo = nullIfBlank(cancellationPolicyInfo);
        if (this.cancellationPolicyInfo == null && newCancellationPolicyInfo != null ||
            this.cancellationPolicyInfo != null && !this.cancellationPolicyInfo.equals(
                newCancellationPolicyInfo)) {
            this.cancellationPolicyInfo = newCancellationPolicyInfo;
            isChanged = true;
        }

        // Integer 필드 비교
        Integer newCancellationDeadline = parseInteger(cancellationDeadline);
        if (this.cancellationDeadline == null && newCancellationDeadline != null ||
            this.cancellationDeadline != null && !this.cancellationDeadline.equals(
                newCancellationDeadline)) {
            this.cancellationDeadline = newCancellationDeadline;
            isChanged = true;
        }

        // x, y 비교
        String newX = nullIfBlank(x);
        if (this.x == null && newX != null ||
            this.x != null && !this.x.equals(newX)) {
            this.x = newX;
            isChanged = true;
        }

        String newY = nullIfBlank(y);
        if (this.y == null && newY != null ||
            this.y != null && !this.y.equals(newY)) {
            this.y = newY;
            isChanged = true;
        }

        String newAreaNm = nullIfBlank(areaNm);
        if (this.areaNm == null && newAreaNm != null ||
            this.areaNm != null && !this.areaNm.equals(newAreaNm)) {
            this.areaNm = newAreaNm;
            isChanged = true;
        }

        return isChanged;
    }
}