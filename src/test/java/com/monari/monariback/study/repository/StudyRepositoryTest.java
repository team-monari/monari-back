package com.monari.monariback.study.repository;

import com.monari.monariback.study.dto.StudyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@SpringBootTest
class StudyRepositoryTest {

    @Autowired
    private StudyRepository studyRepository;

    @Test
    void 기존_페이징() {
        List<StudyDto> studies = studyRepository.findOrderByCreatedAtDesc(10000, 10);

        assertThat(studies).hasSize(10);
    }

    @Test
    void 커버링_인덱스() {
        List<StudyDto> studies = studyRepository.paginationCoveringIndex(10000, 10);

        assertThat(studies).hasSize(10);
    }
}