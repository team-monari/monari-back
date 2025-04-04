package com.monari.monariback.lesson.entity;

import com.monari.monariback.lesson.entity.enurmurated.LessonStatus;
import com.monari.monariback.lesson.entity.enurmurated.SchoolLevel;
import com.monari.monariback.lesson.entity.enurmurated.Subject;
import com.monari.monariback.location.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@Table(name = "lesson")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Location location;

    //TODO : Join «ÿ¡÷±‚
    @Column(name = "teacher_id", nullable = false)
    private Integer teacherId;

    @Column(name = "description", nullable = false)
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

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "school_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    @Column(name = "subject", nullable = false)
    @Enumerated(EnumType.STRING)
    private Subject subject;

    public static Lesson ofCreate(
        final Location location,
        final Integer teacherId,
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
        lesson.teacherId = teacherId;
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
        final Location location,
        final Integer teacherId,
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
        this.location = location;
        this.teacherId = teacherId;
        this.description = description;
        this.amount = amount;
        this.minStudent = minStudent;
        this.maxStudent = maxStudent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
        this.schoolLevel = schoolLevel;
        this.subject = subject;
    }
}
