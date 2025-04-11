package com.monari.monariback.study.repository.impl;

import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.repository.StudyCustomRepository;
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
    public List<StudyDto> findOrderByCreatedAtDesc(Integer pageNum, Integer pageSize) {
        return queryFactory
                .select(Projections.constructor(StudyDto.class,
                        study.id,
                        study.title,
                        study.description,
                        study.subject,
                        study.schoolLevel,
                        study.status,
                        study.createdAt,
                        study.location.locationName,
                        study.location.serviceUrl,
                        study.student.publicId,
                        study.student.name
                        ))
                .from(study)
                .innerJoin(study.location, location)
                .innerJoin(study.student, student)
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public List<StudyDto> findByKeywordOrderByCreatedAtDesc(Integer pageNum, Integer pageSize, String titleKeyword, String descriptionKeyword) {
        return queryFactory
                .select(Projections.constructor(StudyDto.class,
                        study.id,
                        study.title,
                        study.description,
                        study.subject,
                        study.schoolLevel,
                        study.status,
                        study.createdAt,
                        study.location.locationName,
                        study.location.serviceUrl,
                        study.student.publicId,
                        study.student.name
                        ))
                .from(study)
                .innerJoin(study.location, location)
                .innerJoin(study.student, student)
                .where(containsTitleKeyword(titleKeyword))
                .where(containsDescriptionKeyword(descriptionKeyword))
                .orderBy(study.createdAt.desc(), study.id.desc())
                .limit(pageSize)
                .offset((long) pageSize * (pageNum - 1))
                .fetch();
    }

    @Override
    public long countByKeyword(String titleKeyword, String descriptionKeyword) {
        Long count = queryFactory.select(study.count())
                .from(study)
                .where(containsTitleKeyword(titleKeyword))
                .where(containsDescriptionKeyword(descriptionKeyword))
                .fetchOne();

        return count != null ? count : 0L;
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
}
