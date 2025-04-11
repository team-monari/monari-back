package com.monari.monariback.lesson.entity;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.lesson.entity.enurmurated.LessonStatus;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.teacher.entity.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "lesson")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Teacher teacher;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "min_student", nullable = false)
    private Integer minStudent;

    @Column(name = "max_student", nullable = false)
    private Integer maxStudent;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonStatus status = LessonStatus.ACTIVE;

    @Column(name = "school_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    @Column(name = "subject", nullable = false)
    @Enumerated(EnumType.STRING)
    private Subject subject;

    public static Lesson ofCreate(
        final Location location,
        final Teacher teacher,
        final String title,
        final String description,
        final Integer amount,
        final Integer minStudent,
        final Integer maxStudent,
        final LocalDate startDate,
        final LocalDate endDate,
        final LocalDate deadline,
        final SchoolLevel schoolLevel,
        final Subject subject
    ) {
        Lesson lesson = new Lesson();
        lesson.location = location;
        lesson.teacher = teacher;
        lesson.title = title;
        lesson.description = description;
        lesson.amount = amount;
        lesson.minStudent = minStudent;
        lesson.maxStudent = maxStudent;
        lesson.startDate = startDate;
        lesson.endDate = endDate;
        lesson.deadline = deadline;
        lesson.schoolLevel = schoolLevel;
        lesson.subject = subject;
        return lesson;
    }

    public void update(
        final Location newLocation,
        final Teacher newTeacher,
        final String newTitle,
        final String newDescription,
        final Integer newAmount,
        final Integer newMinStudent,
        final Integer newMaxStudent,
        final LocalDate newStartDate,
        final LocalDate newEndDate,
        final LocalDate newDeadline,
        final SchoolLevel newSchoolLevel,
        final Subject newSubject
    ) {
        location = newLocation;
        teacher = newTeacher;
        title = newTitle;
        description = newDescription;
        amount = newAmount;
        minStudent = newMinStudent;
        maxStudent = newMaxStudent;
        startDate = newStartDate;
        endDate = newEndDate;
        deadline = newDeadline;
        schoolLevel = newSchoolLevel;
        subject = newSubject;
    }
}
