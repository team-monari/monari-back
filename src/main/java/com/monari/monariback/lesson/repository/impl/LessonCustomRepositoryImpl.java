package com.monari.monariback.lesson.repository.impl;

import static com.monari.monariback.lesson.entity.QLesson.lesson;

import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LessonCustomRepositoryImpl implements LessonCustomRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 페이징 처리를 위해 전체 강의 목록 중 일부를 조회합니다.
     *
     * @param pageSize 한 페이지당 강의 수
     * @param pageNum  조회할 페이지 번호 (1부터 시작)
     * @return 요청한 페이지에 해당하는 강의 목록
     */
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

    /**
     * 키워드를 포함하는 강의 목록 중 일부를 페이징하여 조회합니다.
     *
     * @param keyword  검색 키워드
     * @param pageSize 한 페이지당 강의 수
     * @param pageNum  조회할 페이지 번호 (1부터 시작)
     * @return 키워드가 포함된 강의 목록 (페이징 적용)
     */
    @Override
    public List<Lesson> searchLessons(
        final String keyword,
        final Integer pageSize,
        final Integer pageNum) {

        return queryFactory.selectFrom(lesson)
            .where(buildKeywordPredicate(keyword))
            .orderBy(lesson.createdAt.desc(), lesson.id.desc())
            .limit(pageSize)
            .offset((long) (pageNum - 1) * pageSize)
            .fetch();
    }

    /**
     * 키워드에 따른 총 페이지 수를 계산합니다.
     *
     * @param pageSize 페이지당 항목 수
     * @param keyword  검색 키워드
     * @return 총 페이지 수
     * @author Hong
     */
    @Override
    public int getTotalLessonPages(
        final int pageSize,
        final String keyword
    ) {

        Long totalCount = queryFactory
            .select(lesson.count())
            .from(lesson)
            .where(buildKeywordPredicate(keyword))
            .fetchFirst();

        if (totalCount == null || totalCount == 0) {
            return 0;
        }

        return (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 키워드에 따른 총 레슨 수를 반환합니다.
     *
     * @param keyword 검색 키워드
     * @return 총 레슨 수
     * @author Hong
     */
    public long getTotalLessonCount(final String keyword) {
        Long count = queryFactory.select(lesson.count())
            .from(lesson)
            .where(buildKeywordPredicate(keyword))
            .fetchOne();

        return count != null ? count : 0L;
    }


    /**
     * 키워드에 따른 검색 조건을 생성합니다.
     *
     * @param keyword 검색 키워드
     * @return BooleanExpression 조건, null이면 조건 없음
     * @author Hong
     */
    private BooleanExpression buildKeywordPredicate(final String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }
        return lesson.title.containsIgnoreCase(keyword)
            .or(lesson.description.containsIgnoreCase(keyword));
    }
}
