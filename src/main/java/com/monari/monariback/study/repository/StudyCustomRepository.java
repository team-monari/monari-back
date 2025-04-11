package com.monari.monariback.study.repository;

import com.monari.monariback.study.dto.StudyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCustomRepository {

    List<StudyDto> findOrderByCreatedAtDesc(Integer pageNum, Integer pageSize);

    List<StudyDto> findByKeywordOrderByCreatedAtDesc(Integer pageNum, Integer pageSize, String titleKeyword, String descriptionKeyword);

    long countByKeyword(String titleKeyword, String descriptionKeyword);
}
