package com.monari.monariback.study.service;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.exception.AuthException;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.dto.request.StudyChangeStatusRequest;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyEditRequest;
import com.monari.monariback.study.dto.response.StudyDetailResponse;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    /**
     * 페이지별 스터디 목록 조회
     * @param pageNum - 페이지 번호
     * @param pageSize - 한 페이지에 조회될 스터디 개수
     * @return Page<StudyResponse>
     * @author Jeong
     */
    @Transactional(readOnly = true)
    public Page<StudyResponse> getStudies(final int pageNum, final int pageSize) {
        List<StudyResponse> content = studyRepository.findOrderByCreatedAtDesc(pageNum, pageSize)
                .stream().map(StudyResponse::from)
                .toList();

        return new PageImpl<>(
                content, PageRequest.of(pageNum - 1, pageSize), studyRepository.count()
        );
    }

    /**
     * 스터디 검색 기반 페이지별 목록 조회
     * @param pageNum - 페이지 번호
     * @param pageSize - 한 페이지에 조회될 스터디 개수
     * @param titleKeyword - 제목 키워드
     * @param descriptionKeyword - 설명 키워드
     * @return Page<StudyResponse>
     * @author Jeong
     */
    @Transactional(readOnly = true)
    public Page<StudyResponse> searchStudies(final int pageNum,
                                             final int pageSize,
                                             final String titleKeyword,
                                             final String descriptionKeyword) {
        List<StudyDto> studies = studyRepository.findByKeywordOrderByCreatedAtDesc(pageNum, pageSize, titleKeyword, descriptionKeyword);
        List<StudyResponse> content = studies.stream()
                .map(StudyResponse::from)
                .toList();

        long totalStudiesCount = studyRepository.countByKeyword(titleKeyword, descriptionKeyword);

        return new PageImpl<>(
                content, PageRequest.of(pageNum - 1, pageSize), totalStudiesCount
        );
    }

    @Transactional(readOnly = true)
    public StudyDetailResponse getStudy(final Integer studyId) {
        Study study = studyRepository.findByIdWithStudentAndLocation(studyId)
                .orElseThrow(() -> new NotFoundException(STUDY_NOT_FOUND));
        return StudyDetailResponse.from(study);
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
