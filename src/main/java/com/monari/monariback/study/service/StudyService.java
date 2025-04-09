package com.monari.monariback.study.service;

import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyUpdateRequest;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public void createStudy(final StudyCreateRequest request) {
        Location location = locationRepository.findById(request.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 공공장소는 존재하지 않습니다"));

        Study study = Study.ofCreate(
                request.title(),
                request.description(),
                request.subject(),
                request.schoolLevel(),
                location
        );

        studyRepository.save(study);
    }
    
    public List<StudyResponse> readStudies() {
        return studyRepository.findAllWithLocation()
                .stream()
                .map(StudyResponse::from)
                .toList();
    }

    public StudyResponse readStudy(Integer studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다."));

        return StudyResponse.from(study);
    }

    @Transactional
    public void closeStudy(Integer studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다."));

        study.markAsClosed();
    }

    @Transactional
    public void updateStudy(Integer studyId, StudyUpdateRequest request) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다."));
        Location location = locationRepository.findById(request.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 공공장소는 존재하지 않습니다"));

        study.updateStudy(
                request.title(),
                request.description(),
                request.subject(),
                request.schoolLevel(),
                location
        );
    }

}
