package com.monari.monariback.lesson.repository.impl;

import static com.monari.monariback.enrollment.entity.QEnrollment.enrollment;
import static com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus.CANCELLED;
import static com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus.REFUNDED;
import static com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus.REFUND_REQUESTED;
import static com.monari.monariback.lesson.entity.QLesson.lesson;
import static com.monari.monariback.location.entity.QGeneralLocation.generalLocation;
import static com.monari.monariback.teacher.entity.QTeacher.teacher;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.SearchType;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.dto.response.LessonWithTeacherResponse;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonType;
import com.monari.monariback.lesson.repository.LessonCustomRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LessonCustomRepositoryImpl implements LessonCustomRepository {

    private static final OrderSpecifier<?>[] LESSON_DEFAULT_ORDER = {
        lesson.createdAt.desc(),
        lesson.id.desc()
    };
    private final JPAQueryFactory queryFactory;

    private static NumberTemplate<Integer> getCurrentStudent() {
        return Expressions.numberTemplate(Integer.class, "cast({0} as int)",
            JPAExpressions.select(enrollment.id.countDistinct())
                .from(enrollment)
                .where(enrollment.lesson.id.eq(lesson.id)
                    .and(enrollment.status.notIn(REFUND_REQUESTED, REFUNDED, CANCELLED))));
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
        long totalCount = getTotalLessonCount(keyword, null, null, null, null, null);
        return totalCount == 0 ? 0 : (int) Math.ceil((double) totalCount / pageSize);
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
        final Subject subject,
        final Region region,
        final LessonType lessonType,
        final SearchType searchType
    ) {
        Long count = queryFactory
            .select(lesson.count())
            .from(lesson)
            .where(buildKeywordPredicate(keyword, schoolLevel, subject, region, lessonType,
                searchType))
            .fetchOne();

        return count != null ? count : 0L;
    }

    /**
     * 특정 강사의 전체 강의 개수를 반환합니다.
     *
     * @param teacherId 강사의 고유 ID
     * @return 해당 강사의 강의 수 (long 타입 반환)
     */
    @Override
    public Long getTotalLessenByTeacherId(final int teacherId) {
        Long count = queryFactory.select(lesson.count())
            .from(lesson)
            .where(lesson.teacher.id.eq(teacherId))
            .fetchOne();
        return count != null ? count : 0L;
    }

    /**
     * 전체 강의 목록을 학생 수와 함께 페이지 단위로 조회합니다.
     *
     * @param pageSize   페이지당 강의 수
     * @param pageNumber 페이지 번호
     * @return LessonResponse 리스트
     */

    @Override
    public List<LessonResponse> findLessonsWithStudentCount(
        final Integer pageSize,
        final Integer pageNumber
    ) {
        return fetchLessonsWithStudentCount(null, pageSize, pageNumber);
    }

    /**
     * 특정 강사의 강의 목록을 학생 수와 함께 페이지 단위로 조회합니다.
     *
     * @param pageSize   페이지당 강의 수
     * @param pageNumber 페이지 번호 (1부터 시작)
     * @param teacherId  강사의 UUID
     * @param studentId  학생의 UUID
     * @return LessonResponse 리스트
     * @author Hong
     */

    @Override
    public List<LessonResponse> findLessonsWithStudentCountByAuth(
        final Integer pageSize,
        final Integer pageNumber,
        final UUID teacherId,
        final UUID studentId
    ) {
        BooleanExpression whereCondition = teacherId != null
            ? lesson.teacher.publicId.eq(teacherId)
            : enrollment.student.publicId.eq(studentId);
        return fetchLessonsWithStudentCount(whereCondition, pageSize, pageNumber);
    }

    /**
     * 키워드를 기반으로 검색 조건을 생성합니다. 제목 또는 설명에 키워드가 포함되어 있는지를 검사합니다.
     *
     * @param keyword     검색 키워드 (null 또는 공백이면 조건 없이 전체 검색)
     * @param schoolLevel 검색 필터 (null이면 조건 없이 전체 검색)
     * @param subject     검색 필터 (null이면 조건 없이 전체 검색)
     * @param region      검색 필터 (null이면 조건 없이 전체 검색)
     * @param lessonType  검색 필터 (null이면 조건 없이 전체 검색)
     * @param searchType  검색 범위 설정
     * @return QueryDSL의 BooleanExpression 조건
     * @author Hong
     */
    @Override
    public List<LessonResponse> searchLessonsWithStudentCount(
        final String keyword,
        final Integer pageSize,
        final Integer pageNum,
        final SchoolLevel schoolLevel,
        final Subject subject,
        final Region region,
        final LessonType lessonType,
        final SearchType searchType
    ) {
        return fetchLessonsWithStudentCount(
            buildKeywordPredicate(keyword, schoolLevel, subject, region, lessonType, searchType),
            pageSize,
            pageNum
        );
    }

    @Override
    public void updateStatus(List<Integer> lessonIds, LessonStatus lessonStatus) {
        queryFactory
                .update(lesson)
                .set(lesson.status, lessonStatus)
                .where(lesson.id.in(lessonIds))
                .execute();
    }

    /**
     * 강의 ID를 기반으로 강의 정보와 강사 정보를 함께 조회합니다. enrollment 상태를 기준으로 수강 중인 학생 수를 계산하여 포함합니다.
     *
     * @param locationId 강의 ID
     * @return LessonWithTeacherResponse DTO를 Optional로 감싸 반환합니다.
     */
    @Override
    public Optional<LessonWithTeacherResponse> findByLessonIdWithTeacher(final int locationId) {
        return Optional.ofNullable(queryFactory.select(createLessonWithTeacherResponse())
            .from(lesson)
            .leftJoin(lesson.teacher, teacher)
            .leftJoin(lesson.generalLocation, generalLocation)
            .where(lesson.id.eq(locationId))
            .groupBy(lesson.id)
            .fetchFirst()
        );
    }

    /**
     * 전체 강의 개수를 정수(Integer)로 반환합니다.
     *
     * @return 전체 강의 수
     */
    @Override
    public Integer countTotalLessons() {
        Long count = queryFactory
            .select(lesson.count())
            .from(lesson)
            .fetchOne();
        return (int) (count != null ? count : 0);
    }

    private BooleanExpression buildKeywordPredicate(
        final String keyword,
        final SchoolLevel schoolLevel,
        final Subject subject,
        final Region region,
        final LessonType lessonType,
        final SearchType searchType
    ) {
        BooleanExpression condition = null;

        condition = combineConditions(condition, keywordCondition(keyword, searchType));
        condition = combineConditions(condition, schoolLevelCondition(schoolLevel));
        condition = combineConditions(condition, subjectCondition(subject));
        condition = combineConditions(condition, regionCondition(region));
        condition = combineConditions(condition, lessonTypeCondition(lessonType));

        return condition;
    }

    /**
     * 두 BooleanExpression을 and()로 결합합니다.
     *
     * @param base       기존 조건 (null 가능)
     * @param additional 추가 조건 (null 가능)
     * @return 결합된 BooleanExpression 또는 null
     */
    private BooleanExpression combineConditions(BooleanExpression base,
        BooleanExpression additional) {
        if (additional == null) {
            return base;
        }
        return base == null ? additional : base.and(additional);
    }

    /**
     * 주어진 조건과 페이지네이션 정보를 바탕으로 강의 목록을 학생 수와 함께 조회합니다. 공통 쿼리 로직을 캡슐화하여 중복 코드를 줄이고 재사용성을 높입니다.
     *
     * @param whereCondition 검색 및 필터링 조건 (null일 경우 조건 없이 전체 조회)
     * @param pageSize       페이지당 강의 수 (양수여야 함)
     * @param pageNumber     페이지 번호 (1부터 시작)
     * @return LessonResponse 객체의 리스트
     * @author Hong
     */

    private List<LessonResponse> fetchLessonsWithStudentCount(
        final BooleanExpression whereCondition,
        final Integer pageSize,
        final Integer pageNumber
    ) {
        return pagination(
            queryFactory
                .select(createLessonResponse())
                .from(lesson)
                .leftJoin(lesson.enrollments, enrollment)
                .where(whereCondition)
                .orderBy(LESSON_DEFAULT_ORDER),
            pageSize,
            pageNumber
        ).fetch();
    }

    private BooleanExpression lessonTypeCondition(final LessonType lessonType) {
        return lessonType != null ? lesson.lessonType.eq(lessonType) : null;
    }

    private BooleanExpression keywordCondition(final String keyword, final SearchType searchType) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }
        return switch (searchType) {
            case TITLE -> lesson.title.containsIgnoreCase(keyword);
            case DESCRIPTION -> lesson.description.containsIgnoreCase(keyword);
            case ALL -> lesson.title.containsIgnoreCase(keyword)
                .or(lesson.description.containsIgnoreCase(keyword));
        };
    }

    private BooleanExpression schoolLevelCondition(final SchoolLevel schoolLevel) {
        return schoolLevel != null ? lesson.schoolLevel.eq(schoolLevel) : null;
    }

    private BooleanExpression subjectCondition(final Subject subject) {
        return subject != null ? lesson.subject.eq(subject) : null;
    }

    private BooleanExpression regionCondition(final Region region) {
        return region != null ? lesson.region.eq(region) : null;
    }

    private ConstructorExpression<LessonResponse> createLessonResponse() {
        return Projections.constructor(
            LessonResponse.class,
            lesson.id,
            lesson.generalLocation.id,
            lesson.teacher.id,
            lesson.title,
            getCurrentStudent(),
            lesson.description,
            lesson.amount,
            lesson.minStudent,
            lesson.maxStudent,
            lesson.startDate,
            lesson.endDate,
            lesson.deadline,
            lesson.status.stringValue(),
            lesson.region.stringValue(),
            lesson.schoolLevel.stringValue(),
            lesson.subject.stringValue(),
            lesson.lessonType.stringValue()
        );
    }

    private ConstructorExpression<LessonWithTeacherResponse> createLessonWithTeacherResponse() {
        return Projections.constructor(
            LessonWithTeacherResponse.class,
            teacher.publicId,
            lesson.id,
            lesson.generalLocation.id,
            lesson.title,
            getCurrentStudent(),
            lesson.description,
            lesson.amount,
            lesson.minStudent,
            lesson.maxStudent,
            lesson.startDate,
            lesson.endDate,
            lesson.deadline,
            lesson.status.stringValue(),
            lesson.region.stringValue(),
            lesson.schoolLevel.stringValue(),
            lesson.subject.stringValue(),
            lesson.lessonType.stringValue(),
            teacher.name,
            teacher.university,
            teacher.major,
            teacher.career,
            generalLocation.locationName,
            generalLocation.x,
            generalLocation.y,
            generalLocation.serviceUrl
        );
    }

    private <T> JPAQuery<T> pagination(
        final JPAQuery<T> query,
        final Integer pageSize,
        final Integer pageNumber
    ) {
        return query
            .offset((long) (pageNumber - 1) * pageSize)
            .limit(pageSize);
    }
}
