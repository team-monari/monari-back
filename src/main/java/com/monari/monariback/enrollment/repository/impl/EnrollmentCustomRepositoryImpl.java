package com.monari.monariback.enrollment.repository.impl;

import static com.monari.monariback.enrollment.entity.QEnrollment.enrollment;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.enrollment.repository.EnrollmentCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class EnrollmentCustomRepositoryImpl implements EnrollmentCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Integer countCurrentStudentByLessonId(final Integer lessonId) {
        final Long count = queryFactory.select(enrollment.count())
            .from(enrollment)
            .where(enrollment.lesson.id.eq(lessonId).
                and(enrollment.status.notIn(
                        EnrollmentStatus.REFUND_REQUESTED,
                        EnrollmentStatus.REFUNDED,
                        EnrollmentStatus.CANCELLED
                    )
                )
            )
            .fetchOne();
        return (int) (count != null ? count : 0);
    }

    @Override
    public boolean existsByStudentIdAndLessonId(final Integer studentId, final Integer lessonId) {
        return queryFactory.selectOne()
            .from(enrollment)
            .where(enrollment.lesson.id.eq(lessonId).and(enrollment.student.id.eq(studentId)))
            .fetchFirst() != null;

    }

    @Override
    public List<Enrollment> findAllByLessonId(
        final Integer lessonId
    ) {
        return queryFactory.selectFrom(enrollment)
            .leftJoin(enrollment.student).fetchJoin()
            .where(enrollment.lesson.id.eq(lessonId))
            .fetch();
    }

    @Override
    public Optional<Enrollment> findByStudentAndLesson(
        final UUID studentId,
        final Integer lessonId
    ) {
        return Optional.ofNullable(queryFactory.selectFrom(enrollment)
            .where(enrollment.student.publicId.eq(studentId)
                .and(enrollment.lesson.id.eq(lessonId))
            )
            .leftJoin(enrollment.lesson)
            .fetchFirst());
    }


    @Override
    public Long countByStudentId(final Integer studentId) {

        Long count = queryFactory.select(enrollment.count())
            .from(enrollment)
            .where(enrollment.student.id.eq(studentId))
            .fetchFirst();
        return count != null ? count : 0L;

    }

    @Override
    public Optional<Enrollment> findByLessonIdAndStudentId(
        final Integer studentId,
        final Integer lessonId
    ) {
        return Optional.ofNullable(queryFactory.selectFrom(enrollment)
            .where(enrollment.student.id.eq(studentId)
                .and(enrollment.lesson.id.eq(lessonId))).fetchFirst());
    }

    @Override
    public void updateFinalPrice(List<Integer> enrollmentIds, int finalPrice, EnrollmentStatus status) {
        queryFactory
                .update(enrollment)
                .set(enrollment.finalPrice, finalPrice)
                .set(enrollment.status, status)
                .where(enrollment.id.in(enrollmentIds))
                .execute();
    }


}
