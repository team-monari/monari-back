package com.monari.monariback.study.repository.impl;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.repository.StudyCustomRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.monari.monariback.location.entity.QLocation.location;
import static com.monari.monariback.student.entity.QStudent.student;
import static com.monari.monariback.study.entity.QStudy.study;

@Repository
@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StudyDto> findOrderByCreatedAtDesc(int pageNum, int pageSize) {
        return queryFactory
                .select(createStudyDto())
                .from(study)
                .innerJoin(study.location, location)
                .innerJoin(study.student, student)
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public List<StudyDto> findByKeywordsOrderByCreatedAtDesc(int pageNum, int pageSize, String titleKeyword, String descriptionKeyword, SchoolLevel schoolLevel, Subject subject, Region region) {
        return queryFactory
                .select(createStudyDto())
                .from(study)
                .innerJoin(study.location, location)
                .innerJoin(study.student, student)
                .where(createFilterConditions(titleKeyword, descriptionKeyword, schoolLevel, subject, region))
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public List<StudyDto> findByStudentIdOrderByCreatedAtDesc(int pageNum, int pageSize, Integer studentId) {
        return queryFactory
                .select(createStudyDto())
                .from(study)
                .innerJoin(study.location, location)
                .innerJoin(study.student, student)
                .where(study.student.id.eq(studentId))
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public long countByKeywords(String titleKeyword, String descriptionKeyword, SchoolLevel schoolLevel, Subject subject, Region region) {
        Long count = queryFactory.select(study.count())
                .from(study)
                .where(createFilterConditions(titleKeyword, descriptionKeyword, schoolLevel, subject, region))
                .fetchOne();

        return count != null ? count : 0L;
    }

    @Override
    public long countByStudentId(Integer studentId) {
        Long count = queryFactory.select(study.count())
                .from(study)
                .where(study.student.id.eq(studentId))
                .fetchOne();

        return count != null ? count : 0L;
    }

    private ConstructorExpression<StudyDto> createStudyDto() {
        return Projections.constructor(StudyDto.class,
                study.id,
                study.title,
                study.description,
                study.subject,
                study.schoolLevel,
                study.region,
                study.status,
                study.createdAt,
                study.location.locationName,
                study.location.serviceUrl,
                study.student.publicId,
                study.student.name
        );
    }

    private BooleanExpression[] createFilterConditions(String titleKeyword, String descriptionKeyword,
                                                       SchoolLevel schoolLevel, Subject subject, Region region) {
        return new BooleanExpression[] {
                containsTitleKeyword(titleKeyword),
                containsDescriptionKeyword(descriptionKeyword),
                eqSchoolLevel(schoolLevel),
                eqSubject(subject),
                eqRegion(region)
        };
    }


    private BooleanExpression containsTitleKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }
        return study.title.containsIgnoreCase(keyword);
    }

    private BooleanExpression containsDescriptionKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }
        return study.description.containsIgnoreCase(keyword);
    }

    private BooleanExpression eqSchoolLevel(SchoolLevel schoolLevel) {
        return schoolLevel == null ? null : study.schoolLevel.eq(schoolLevel);
    }

    private BooleanExpression eqSubject(Subject subject) {
        return subject == null ? null : study.subject.eq(subject);
    }

    private BooleanExpression eqRegion(Region region) {
        return region == null ? null : study.region.eq(region);
    }
}
