package com.monari.monariback.study.repository;

import com.monari.monariback.study.dto.StudyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCustomRepository {

    List<StudyDto> findOrderByCreatedAtDesc(int pageNum, int pageSize);

    List<StudyDto> findByKeywordOrderByCreatedAtDesc(int pageNum, int pageSize, String titleKeyword, String descriptionKeyword);

    List<StudyDto> findByStudentIdOrderByCreatedAtDesc(int pageNum, int pageSize, Integer studentId);

    long countByKeyword(String titleKeyword, String descriptionKeyword);

    long countByStudentId(Integer studentId);
}
