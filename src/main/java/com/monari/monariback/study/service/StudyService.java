package com.monari.monariback.study.service;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.exception.AuthException;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import com.monari.monariback.study.dto.request.StudyChangeStatusRequest;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyEditRequest;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.monari.monariback.common.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final LocationRepository locationRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void createStudy(final Accessor accessor, final StudyCreateRequest request) {
        Location location = locationRepository.findById(request.locationId())
                .orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));

        Student student = studentRepository.findByPublicId(accessor.getPublicId())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));

        Study study = Study.ofCreate(
                request.title(),
                request.description(),
                request.subject(),
                request.schoolLevel(),
                location,
                student
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
    public void changeStudyStatus(final Accessor accessor,
                                  final Integer studyId,
                                  final StudyChangeStatusRequest request) {
        Study study = studyRepository.findByIdWithStudent(studyId)
                .orElseThrow(() -> new NotFoundException(STUDY_NOT_FOUND));

        validateStudyCreator(accessor, study);

        study.updateStudyStatus(request.status());
    }

    @Transactional
    public void editStudy(final Accessor accessor,
                          final Integer studyId,
                          final StudyEditRequest request) {
        Study study = studyRepository.findByIdWithStudent(studyId)
                .orElseThrow(() -> new NotFoundException(STUDY_NOT_FOUND));

        validateStudyCreator(accessor, study);

        Location location = locationRepository.findById(request.locationId())
                .orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));

        study.updateStudy(
                request.title(),
                request.description(),
                request.subject(),
                request.schoolLevel(),
                location
        );
    }

    private void validateStudyCreator(Accessor accessor, Study study) {
        UUID studyCreatorPublicId = study.getStudent().getPublicId();
        if (!accessor.getPublicId().equals(studyCreatorPublicId)) {
            throw new AuthException(AUTH_FORBIDDEN);
        }
    }

}
