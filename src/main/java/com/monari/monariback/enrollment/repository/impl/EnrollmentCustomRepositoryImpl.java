package com.monari.monariback.enrollment.repository.impl;

import static com.monari.monariback.enrollment.entity.QEnrollment.enrollment;

import com.monari.monariback.enrollment.repository.EnrollmentCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class EnrollmentCustomRepositoryImpl implements EnrollmentCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Integer countByLessonId(final Integer lessonId) {
        final Long count = queryFactory.select(enrollment.count())
            .where(enrollment.lesson.id.eq(lessonId))
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
}
