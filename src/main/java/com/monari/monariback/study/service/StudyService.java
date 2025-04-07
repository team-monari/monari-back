package com.monari.monariback.study.service;

import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final LocationRepository locationRepository;

    /**
     * 스터디 생성
     * @param studyDto 스터디 생성 요청 DTO
     * @author Jeong
     */
    @Transactional
    public void createStudy(final StudyCreateRequest studyDto) {
        Location location = locationRepository.findById(studyDto.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 공공장소는 존재하지 않습니다"));

        Study study = Study.ofCreate(
                studyDto.title(),
                studyDto.description(),
                studyDto.subject(),
                studyDto.schoolLevel(),
                location
        );

        studyRepository.save(study);
    }
}
