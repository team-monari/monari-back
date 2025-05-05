package com.monari.monariback.study.service;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.error.ErrorCode;
import com.monari.monariback.common.exception.AuthException;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.location.entity.GeneralLocation;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.GeneralLocationRepository;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.dto.request.StudyChangeStatusRequest;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyEditRequest;
import com.monari.monariback.study.dto.request.StudySearchRequest;
import com.monari.monariback.study.dto.response.StudyDetailResponse;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.enumerated.StudyType;
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
    private final GeneralLocationRepository generalLocationRepository;

    @Transactional
    public void createStudy(final Accessor accessor, final StudyCreateRequest request) {
        StudyType studyType = getStudyType(request.locationId(), request.generalLocationId());
        Location location = null;
        GeneralLocation generalLocation = null;

        if (studyType.equals(StudyType.OFFLINE)) {
            if (request.locationId() != null) {
                location = locationRepository.findById(request.locationId())
                                .orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
            } else if (request.generalLocationId() != null) {
                generalLocation = generalLocationRepository.findById(request.generalLocationId())
                                    .orElseThrow(() -> new NotFoundException(ErrorCode.LOCATION_NOT_FOUND));
            }
        }

        Student student = studentRepository.findByPublicId(accessor.getPublicId())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));

        Study study = Study.ofCreate(
                request.title(),
                request.description(),
                request.subject(),
                request.schoolLevel(),
                request.region(),
                studyType,
                location,
                generalLocation,
                student
        );

        studyRepository.save(study);
    }

    private static StudyType getStudyType(Integer locationId, Integer generalLocationId) {
        if (locationId == null && generalLocationId == null) {
            return StudyType.ONLINE;
        }
        return StudyType.OFFLINE;
    }

    /**
     * 페이지별 스터디 목록 조회
     * 최신순 정렬
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
     * 최신순 정렬
     * @param int pageNum - 페이지 번호
     * @param int pageSize - 한 페이지에 조회될 스터디 개수
     * @param String titleKeyword - 제목 키워드
     * @param String descriptionKeyword - 설명 키워드
     * @param SchoolLevel schoolLevel, - 학교급
     * @param Subject subject, - 과목
     * @param Region region - 지역구
     * @return Page<StudyResponse>
     * @author Jeong
     */
    @Transactional(readOnly = true)
    public Page<StudyResponse> searchStudies(final int pageNum, final int pageSize, final StudySearchRequest request) {
        List<StudyDto> studies = studyRepository.findByKeywordsOrderByCreatedAtDesc(
                pageNum,
                pageSize,
                request.titleKeyword(),
                request.descriptionKeyword(),
                request.schoolLevel(),
                request.subject(),
                request.region()
        );
        List<StudyResponse> content = studies.stream()
                .map(StudyResponse::from)
                .toList();

        long totalStudyCount = studyRepository.countByKeywords(
                request.titleKeyword(),
                request.descriptionKeyword(),
                request.schoolLevel(),
                request.subject(),
                request.region()
        );

        return new PageImpl<>(
                content, PageRequest.of(pageNum - 1, pageSize), totalStudyCount
        );
    }

    /**
     * 개설한 스터디 목록 조회
     * 최신순 정렬
     * @param accessor - 학생 회원
     * @param pageNum - 페이지 번호
     * @param pageSize - 한 페이지에 조회될 스터디 개수
     * @return Page<StudyResponse>
     * @author Jeong
     */
    @Transactional(readOnly = true)
    public Page<StudyResponse> getMyStudies(final Accessor accessor,
                                            final int pageNum,
                                            final int pageSize) {
        //학생 회원 조회
        Student student = studentRepository.findByPublicId(accessor.getPublicId())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
        Integer studentId = student.getId();

        //한 페이지에 반환할 스터디 리스트 조회
        List<StudyDto> myStudies = studyRepository.findByStudentIdOrderByCreatedAtDesc(pageNum, pageSize, studentId);
        List<StudyResponse> content = myStudies.stream()
                .map(StudyResponse::from)
                .toList();

        //학생이 개설한 전체 스터디 개수 조회
        long totalMyStudyCount = studyRepository.countByStudentId(studentId);

        return new PageImpl<>(
                content, PageRequest.of(pageNum - 1, pageSize), totalMyStudyCount
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

        StudyType studyType = getStudyType(request.locationId(), request.generalLocationId());
        Location location = null;
        GeneralLocation generalLocation = null;

        if (studyType.equals(StudyType.OFFLINE)) {
            if (request.locationId() != null) {
                location = locationRepository.findById(request.locationId())
                                .orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
            } else if (request.generalLocationId() != null) {
                generalLocation = generalLocationRepository.findById(request.generalLocationId())
                                    .orElseThrow(() -> new NotFoundException(ErrorCode.LOCATION_NOT_FOUND));
            }
        }

        study.updateStudy(
                request.title(),
                request.description(),
                request.subject(),
                request.schoolLevel(),
                studyType,
                location,
                generalLocation
        );
    }

    private void validateStudyCreator(Accessor accessor, Study study) {
        UUID studyCreatorPublicId = study.getStudent().getPublicId();
        if (!accessor.getPublicId().equals(studyCreatorPublicId)) {
            throw new AuthException(AUTH_FORBIDDEN);
        }
    }

}
