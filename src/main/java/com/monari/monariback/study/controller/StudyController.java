package com.monari.monariback.study.controller;

import com.monari.monariback.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monari.monariback.study.dto.request.StudyCreateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/studies")
public class StudyController {

    private final StudyService studyService;

    @PostMapping()
    public ResponseEntity<Void> createStudy(@RequestBody final StudyCreateRequest studyDto) {
        studyService.createStudy(studyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
