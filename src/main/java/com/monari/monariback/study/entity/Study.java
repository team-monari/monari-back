package com.monari.monariback.study.entity;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.study.entity.enumerated.StudyStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public static Study ofCreate(
            String title,
            String description,
            Subject subject,
            SchoolLevel schoolLevel,
            Location location) {

        Study study = new Study();
        study.title = title;
        study.description = description;
        study.subject = subject;
        study.schoolLevel = schoolLevel;
        study.location = location;
        study.status = StudyStatus.ACTIVE;
        return study;
    }

}
