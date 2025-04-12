package com.monari.monariback.study.controller;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyStudent;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.study.dto.request.StudyChangeStatusRequest;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyEditRequest;
import com.monari.monariback.study.dto.response.StudyDetailResponse;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/studies")
public class StudyController {

    private final StudyService studyService;

    @OnlyStudent
    @PostMapping
    public ResponseEntity<Void> createStudy(@Auth final Accessor accessor,
                                            @RequestBody @Valid final StudyCreateRequest request) {
        studyService.createStudy(accessor, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 페이지별 스터디 목록 조회
     * @param pageNum - 페이지 번호
     * @param pageSize - 한 페이지에 조회될 스터디 개수
     * @author Jeong
     */
    @GetMapping
    public ResponseEntity<Page<StudyResponse>> getStudies(
            @RequestParam(name = "pageNum", defaultValue = "1") final int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "6") final int pageSize
    ) {
        Page<StudyResponse> response = studyService.getStudies(pageNum, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 스터디 검색 기반 페이지별 목록 조회
     * @param pageNum - 페이지 번호
     * @param pageSize - 한 페이지에 조회될 스터디 개수
     * @param titleKeyword - 제목 키워드
     * @param descriptionKeyword - 설명 키워드
     * @author Jeong
     */
    @GetMapping("/search")
    public ResponseEntity<Page<StudyResponse>> searchStudies(
            @RequestParam(name = "pageNum", defaultValue = "1") final int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "6") final int pageSize,
            @RequestParam(name = "titleKeyword", required = false) String titleKeyword,
            @RequestParam(name = "descriptionKeyword", required = false) String descriptionKeyword
    ) {
        Page<StudyResponse> response = studyService.searchStudies(pageNum, pageSize, titleKeyword, descriptionKeyword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 내가 개설한 스터디 목록 조회
     * @param accessor - 학생 회원
     * @param pageNum - 페이지 번호
     * @param pageSize - 한 페이지에 조회될 스터디 개수
     * @author Jeong
     */
    @OnlyStudent
    @GetMapping("/me")
    public ResponseEntity<Page<StudyResponse>> getMyStudies(
            @Auth final Accessor accessor,
            @RequestParam(name = "pageNum", defaultValue = "1") final int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "6") final int pageSize
    ) {
        Page<StudyResponse> response = studyService.getMyStudies(accessor, pageNum, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<StudyDetailResponse> getStudy(@PathVariable("studyId") final Integer studyId) {
        StudyDetailResponse response = studyService.getStudy(studyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @OnlyStudent
    @PatchMapping("/{studyId}/status")
    public ResponseEntity<Void> changeStudyStatus(@Auth final Accessor accessor,
                                                  @PathVariable("studyId") final Integer studyId,
                                                  @RequestBody @Valid final StudyChangeStatusRequest request) {
        studyService.changeStudyStatus(accessor, studyId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @OnlyStudent
    @PutMapping("/{studyId}")
    public ResponseEntity<Void> editStudy(@Auth final Accessor accessor,
                                          @PathVariable("studyId") final Integer studyId,
                                          @RequestBody @Valid final StudyEditRequest request) {
        studyService.editStudy(accessor, studyId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
