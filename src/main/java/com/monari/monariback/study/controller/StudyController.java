package com.monari.monariback.study.controller;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyStudent;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyUpdateRequest;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/studies")
public class StudyController {

    private final StudyService studyService;

    @OnlyStudent
    @PostMapping
    public ResponseEntity<Void> createStudy(@Auth final Accessor accessor,
                                            @RequestBody @Valid final StudyCreateRequest request) {
        studyService.createStudy(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<StudyResponse>> readStudies() {
        List<StudyResponse> responses = studyService.readStudies();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<StudyResponse> readStudy(@PathVariable("studyId") Integer studyId) {
        StudyResponse response = studyService.readStudy(studyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @OnlyStudent
    @PostMapping("/{studyId}/closed")
    public ResponseEntity<Void> closeStudy(@Auth final Accessor accessor,
                                           @PathVariable("studyId") final Integer studyId) {
        studyService.closeStudy(accessor, studyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @OnlyStudent
    @PutMapping("/{studyId}")
    public ResponseEntity<Void> editStudy(@Auth final Accessor accessor,
                                          @PathVariable("studyId") final Integer studyId,
                                          @RequestBody @Valid final StudyUpdateRequest request) {
        studyService.editStudy(accessor, studyId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
