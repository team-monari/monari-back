package com.monari.monariback.lesson.repository.impl;

import static com.monari.monariback.lesson.entity.QLesson.lesson;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
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

    /**
     * 전체 강의 목록에서 일부만 조회하여 페이징을 적용한 결과를 반환합니다.
     *
     * @param pageSize 페이지당 강의 수
     * @param pageNum  조회할 페이지 번호 (1부터 시작)
     * @return 요청한 페이지에 해당하는 강의 목록
     * @author Hong
     */
    @Override
    public List<Lesson> findLessonsByPageSize(
        final Integer pageSize,
        final Integer pageNum
    ) {
        // 페이징 처리를 위한 공통 메서드 사용
        return fetchLessonsWithPaging(null, pageSize, pageNum, null, null);
    }

    /**
     * 검색 키워드를 기반으로 한 강의 목록을 페이징 처리하여 반환합니다.
     *
     * @param keyword  검색 키워드 (제목 또는 설명에 포함되는 값)
     * @param pageSize 페이지당 강의 수
     * @param pageNum  조회할 페이지 번호 (1부터 시작)
     * @return 검색 조건에 맞는 강의 목록 (페이징 적용)
     * @author Hong
     */
    @Override
    public List<Lesson> searchLessons(
        final String keyword,
        final Integer pageSize,
        final Integer pageNum,
        final SchoolLevel schoolLevel,
        final Subject subject
    ) {
        // 페이징 처리를 위한 공통 메서드 사용
        return fetchLessonsWithPaging(keyword, pageSize, pageNum, schoolLevel, subject);
    }

    /**
     * 전체 강의 수 또는 키워드에 해당하는 강의 수를 기준으로 총 페이지 수를 계산합니다.
     *
     * @param pageSize 페이지당 강의 수
     * @param keyword  검색 키워드 (null 또는 빈 문자열일 경우 전체 강의 기준)
     * @return 총 페이지 수 (소수점 반올림)
     * @author Hong
     */
    @Override
    public int getTotalLessonPages(
        final int pageSize,
        final String keyword
    ) {
        long totalCount = getTotalLessonCount(keyword, null, null);
        return totalCount == 0 ? 0 : (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 특정 강사의 ID에 해당하는 강의 목록을 페이징하여 조회합니다.
     *
     * @param teacherId 강사의 내부 DB ID
     * @param pageSize  페이지당 강의 수
     * @param pageNum   조회할 페이지 번호
     * @return 해당 강사가 생성한 강의 목록 (페이징 적용)
     * @author Hong
     */
    @Override
    public List<Lesson> findAllByTeacherId(
        final int teacherId,
        final int pageSize,
        final int pageNum
    ) {
        return queryFactory
            .selectFrom(lesson)
            .where(lesson.teacher.id.eq(teacherId))
            .orderBy(
                lesson.createdAt.desc(),  // 최신순 정렬
                lesson.id.desc()
            )
            .limit(pageSize)
            .offset((long) (pageNum - 1) * pageSize)
            .fetch();
    }

    /**
     * 전체 또는 검색 키워드에 해당하는 강의 수를 반환합니다.
     *
     * @param keyword 검색 키워드 (null일 경우 전체 개수 반환)
     * @return 총 강의 수
     * @author Hong
     */
    @Override
    public long getTotalLessonCount(
        final String keyword,
        final SchoolLevel schoolLevel,
        final Subject subject) {
        Long count = queryFactory
            .select(lesson.count())
            .from(lesson)
            .where(buildKeywordPredicate(keyword, schoolLevel, subject))
            .fetchOne();

        return count != null ? count : 0L;
    }

    /**
     * 내부적으로 페이징을 처리하며 강의 목록을 조회합니다.
     *
     * @param keyword     검색 키워드 (null 가능)
     * @param pageSize    페이지당 강의 수
     * @param pageNum     조회할 페이지 번호
     * @param schoolLevel 검색 필터(null 또는 공백이면 조건 없이 전체 검색)
     * @param subject     검색 필터(null 또는 공백이면 조건 없이 전체 검색)
     * @return 페이징 적용된 강의 목록
     * @author Hong
     */
    private List<Lesson> fetchLessonsWithPaging(
        final String keyword,
        final Integer pageSize,
        final Integer pageNum,
        final SchoolLevel schoolLevel,
        final Subject subject) {
        return queryFactory
            .selectFrom(lesson)
            .where(buildKeywordPredicate(keyword, schoolLevel, subject))
            .orderBy(
                lesson.createdAt.desc(),
                lesson.id.desc()
            )
            .limit(pageSize)
            .offset((long) (pageNum - 1) * pageSize)
            .fetch();
    }

    /**
     * 키워드를 기반으로 검색 조건을 생성합니다. 제목 또는 설명에 키워드가 포함되어 있는지를 검사합니다.
     *
     * @param keyword     검색 키워드 (null 또는 공백이면 조건 없이 전체 검색)
     * @param schoolLevel 검색 필터(null 또는 공백이면 조건 없이 전체 검색)
     * @param subject     검색 필터(null 또는 공백이면 조건 없이 전체 검색)
     * @return QueryDSL의 BooleanBuilder 조건
     * @author Hong
     */
    private BooleanBuilder buildKeywordPredicate(
        final String keyword,
        final SchoolLevel schoolLevel,
        final Subject subject) {
        BooleanBuilder builder = new BooleanBuilder();

        if (keyword != null && !keyword.trim().isEmpty()) {
            builder.and(
                lesson.title.containsIgnoreCase(keyword)
                    .or(lesson.description.containsIgnoreCase(keyword))
            );
        }

        if (schoolLevel != null) {
            builder.and(lesson.schoolLevel.eq(schoolLevel));
        }

        if (subject != null) {
            builder.and(lesson.subject.eq(subject));
        }
        return builder;
    }
}
