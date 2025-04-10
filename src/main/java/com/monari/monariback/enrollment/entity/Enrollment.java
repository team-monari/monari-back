package com.monari.monariback.enrollment.entity;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.student.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "enrollment", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "lesson_id"})}
)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Lesson lesson;

    public static Enrollment ofCreate(
        final Student student,
        final Lesson lesson
    ) {
        Enrollment enrollment = new Enrollment();
        enrollment.student = student;
        enrollment.lesson = lesson;
        return enrollment;
    }
}
