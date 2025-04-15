package com.monari.monariback.enrollment.repository.impl;

import static com.monari.monariback.enrollment.entity.QEnrollment.enrollment;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.repository.EnrollmentCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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

    @Override
    public List<Enrollment> findAllByLessonId(
        final Integer lessonId
    ) {
        return queryFactory.selectFrom(enrollment)
            .where(enrollment.lesson.id.eq(lessonId))
            .fetch();
    }


    @Override
    public List<Enrollment> findAllByStudentIdWithPagination(
        final Integer studentId,
        final Integer pageSize,
        final Integer pageNumber
    ) {
        return queryFactory.selectFrom(enrollment)
            .where(enrollment.student.id.eq(studentId))
            .limit(pageSize)
            .offset(getOffset(pageSize, pageNumber))
            .fetch();
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
    public Enrollment findByLessonIdAndStudentId(
        final Integer studentId,
        final Integer lessonId
    ) {
        return queryFactory.selectFrom(enrollment)
            .where(enrollment.student.id.eq(studentId)
                .and(enrollment.lesson.id.eq(lessonId))).fetchFirst();
    }

    private long getOffset(final Integer pageSize, final Integer pageNumber) {
        return (long) (pageNumber - 1) * pageSize;
    }

}
