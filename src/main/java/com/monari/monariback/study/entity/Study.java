package com.monari.monariback.study.entity;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.study.enumerated.StudyStatus;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public static Study ofCreate(
            String title,
            String description,
            Subject subject,
            SchoolLevel schoolLevel,
            Location location,
            Student student
    ) {
        Study study = new Study();
        study.title = title;
        study.description = description;
        study.subject = subject;
        study.schoolLevel = schoolLevel;
        study.status = StudyStatus.ACTIVE;
        study.location = location;
        study.student = student;
        return study;
    }

    public void updateStudyStatus(StudyStatus status) {
        this.status = status;
    }

    public void updateStudy(
            String title,
            String description,
            Subject subject,
            SchoolLevel schoolLevel,
            Location location
    ) {
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.schoolLevel = schoolLevel;
        this.location = location;
    }

}
