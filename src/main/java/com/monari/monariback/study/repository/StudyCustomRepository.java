package com.monari.monariback.study.repository;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.enumerated.StudyType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCustomRepository {

    List<StudyDto> findOrderByCreatedAtDesc(int pageNum, int pageSize);

    List<StudyDto> paginationCoveringIndex(int pageNum, int pageSize);

    List<StudyDto> findByKeywordsOrderByCreatedAtDesc(
            int pageNum,
            int pageSize,
            String titleKeyword,
            String descriptionKeyword,
            SchoolLevel schoolLevel,
            Subject subject,
            Region region,
            StudyType studyType
    );

    List<StudyDto> findByStudentIdOrderByCreatedAtDesc(int pageNum, int pageSize, Integer studentId);

    long countByKeywords(
            String titleKeyword,
            String descriptionKeyword,
            SchoolLevel schoolLevel,
            Subject subject,
            Region region,
            StudyType studyType
    );

    long countByStudentId(Integer studentId);
}
