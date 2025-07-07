package com.monari.monariback.study.repository.impl;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.enumerated.StudyType;
import com.monari.monariback.study.repository.StudyCustomRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.monari.monariback.location.entity.QGeneralLocation.generalLocation;
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
                .leftJoin(study.location, location)
                .leftJoin(study.generalLocation, generalLocation)
                .innerJoin(study.student, student)
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public List<StudyDto> paginationCoveringIndex(int pageNum, int pageSize) {
        List<Integer> ids = queryFactory
                .select(study.id)
                .from(study)
                .orderBy(study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(createStudyDto())
                .from(study)
                .where(study.id.in(ids))
                .orderBy(study.id.desc())
                .fetch();
    }

    @Override
    public List<StudyDto> findByKeywordsOrderByCreatedAtDesc(int pageNum, int pageSize, String titleKeyword,
                                                             String descriptionKeyword, SchoolLevel schoolLevel, Subject subject,
                                                             Region region, StudyType studyType) {
        return queryFactory
                .select(createStudyDto())
                .from(study)
                .leftJoin(study.location, location)
                .leftJoin(study.generalLocation, generalLocation)
                .innerJoin(study.student, student)
                .where(createFilterConditions(titleKeyword, descriptionKeyword, schoolLevel, subject, region, studyType))
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
                .leftJoin(study.location, location)
                .leftJoin(study.generalLocation, generalLocation)
                .innerJoin(study.student, student)
                .where(study.student.id.eq(studentId))
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public long countByKeywords(String titleKeyword, String descriptionKeyword, SchoolLevel schoolLevel,
                                Subject subject, Region region, StudyType studyType) {
        Long count = queryFactory.select(study.count())
                .from(study)
                .where(createFilterConditions(titleKeyword, descriptionKeyword, schoolLevel, subject, region, studyType))
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
                study.studyType,
                study.createdAt,
                study.location.id,
                study.generalLocation.id,
                study.student.publicId,
                study.student.name
        );
    }

    private BooleanExpression[] createFilterConditions(String titleKeyword, String descriptionKeyword,
                                                       SchoolLevel schoolLevel, Subject subject, Region region, StudyType studyType) {
        return new BooleanExpression[] {
                containsTitleKeyword(titleKeyword),
                containsDescriptionKeyword(descriptionKeyword),
                eqSchoolLevel(schoolLevel),
                eqSubject(subject),
                eqRegion(region),
                eqStudyType(studyType)
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

    private BooleanExpression eqStudyType(StudyType studyType) {
        return studyType == null ? null : study.studyType.eq(studyType);
    }
}
