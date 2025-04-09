package com.monari.monariback.study.controller;

import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.dto.request.StudyUpdateRequest;
import com.monari.monariback.study.dto.response.StudyResponse;
import com.monari.monariback.study.service.StudyService;
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

    @PostMapping()
    public ResponseEntity<Void> createStudy(@RequestBody final StudyCreateRequest request) {
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

    @PostMapping("/{studyId}/closed")
    public ResponseEntity<Void> closeStudy (@PathVariable("studyId") Integer studyId) {
        studyService.closeStudy(studyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{studyId}")
    public ResponseEntity<Void> updateStudy (@PathVariable("studyId") Integer studyId,
                                             @RequestBody StudyUpdateRequest request) {
        studyService.updateStudy(studyId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
