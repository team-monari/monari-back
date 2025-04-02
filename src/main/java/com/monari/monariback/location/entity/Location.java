package com.monari.monariback.location.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
    private Long id;

    @Column(name = "service_subcategory")
    private String serviceSubcategory;

    @Column(name = "service_status")
    private String serviceStatus;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "url")
    private String serviceUrl;

    @Column(name = "service_start_date_time")
    private LocalDateTime serviceStartDateTime;

    @Column(name = "service_end_date_time")
    private LocalDateTime serviceEndDateTime;

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
}