package com.monari.monariback.lesson.repository.impl;

import static com.monari.monariback.lesson.entity.QLesson.lesson;

import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LessonCustomRepositoryImpl implements LessonCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Lesson> findLessonsByPageSize(
        final Integer pageSize,
        final Integer pageNum) {
        return queryFactory.selectFrom(lesson)
            .orderBy(lesson.createdAt.desc(), lesson.id.desc())
            .limit(pageSize)
            .offset((long) (pageNum - 1) * pageSize)
            .fetch();
    }

    @Override
    public List<Lesson> searchLessons(
        final String keyword,
        final Integer pageSize,
        final Integer pageNum) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.or(lesson.description.containsIgnoreCase(keyword));
        builder.or(lesson.title.containsIgnoreCase(keyword));

        return queryFactory.selectFrom(lesson)
            .where(builder)
            .orderBy(lesson.createdAt.desc(), lesson.id.desc())
            .limit(pageSize)
            .offset((long) (pageNum - 1) * pageSize)
            .fetch();
    }

    @Override
    public int getTotalLessonPages(final int pageSize) {
        Long totalCount = queryFactory
            .select(lesson.count())
            .from(lesson)
            .fetchOne();

        if (totalCount == null || totalCount == 0) {
            return 0;
        }

        return (int) Math.ceil((double) totalCount / pageSize);
    }
}
