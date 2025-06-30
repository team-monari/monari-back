package com.monari.monariback.lesson.entity;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonType;
import com.monari.monariback.location.entity.GeneralLocation;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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
    private GeneralLocation generalLocation;

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

    @Column(name = "region", nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonStatus status = LessonStatus.ACTIVE;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @Column(name = "school_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    @Column(name = "subject", nullable = false)
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "lesson")
    private List<Enrollment> enrollments;

    public static Lesson ofCreate(
        final GeneralLocation location,
        final Teacher teacher,
        final String title,
        final String description,
        final Integer amount,
        final Integer minStudent,
        final Integer maxStudent,
        final LocalDate startDate,
        final LocalDate endDate,
        final LocalDate deadline,
        final Region region,
        final SchoolLevel schoolLevel,
        final Subject subject,
        final LessonType lessonType
    ) {
        Lesson lesson = new Lesson();
        lesson.generalLocation = location;
        lesson.teacher = teacher;
        lesson.title = title;
        lesson.description = description;
        lesson.amount = amount;
        lesson.minStudent = minStudent;
        lesson.maxStudent = maxStudent;
        lesson.startDate = startDate;
        lesson.endDate = endDate;
        lesson.deadline = deadline;
        lesson.region = region;
        lesson.schoolLevel = schoolLevel;
        lesson.subject = subject;
        lesson.lessonType = lessonType;
        return lesson;
    }

    public void update(
        final GeneralLocation newLocation,
        final Teacher newTeacher,
        final String newTitle,
        final String newDescription,
        final Integer newAmount,
        final Integer newMinStudent,
        final Integer newMaxStudent,
        final LocalDate newStartDate,
        final LocalDate newEndDate,
        final LocalDate newDeadline,
        final Region newRegion,
        final SchoolLevel newSchoolLevel,
        final Subject newSubject,
        final LessonStatus lessonStatus
    ) {
        generalLocation = newLocation;
        teacher = newTeacher;
        title = newTitle;
        description = newDescription;
        amount = newAmount;
        minStudent = newMinStudent;
        maxStudent = newMaxStudent;
        startDate = newStartDate;
        endDate = newEndDate;
        deadline = newDeadline;
        region = newRegion;
        schoolLevel = newSchoolLevel;
        subject = newSubject;
        status = lessonStatus;
    }

    public boolean isMinEnrollmentNotMet() {
        return this.getMinStudent() > this.getEnrollments().size();
    }

    public int calculateFinalPrice() {
        int current = (int) this.getEnrollments()
                .stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.PENDING)
                .count();

        int min = this.getMinStudent();
        int amount = this.getAmount();

        if (current <= min) {
            return amount;
        }

        int extraStudents = current - min;
        double discountRate = extraStudents * 0.025;

        return (int) Math.round(amount * (1 - discountRate));
    }
}
