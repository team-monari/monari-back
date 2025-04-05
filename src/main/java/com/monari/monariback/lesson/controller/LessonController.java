package com.monari.monariback.lesson.controller;

import com.monari.monariback.lesson.dto.request.CreateLessonRequest;
import com.monari.monariback.lesson.dto.request.UpdateLessonRequest;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping()
    public ResponseEntity<?> createLesson(
        @RequestBody final CreateLessonRequest lessonDto
    ) {
        return lessonService.create(lessonDto);
    }

    @PatchMapping("/{lessonId}")
    public ResponseEntity<?> updateLesson(
        @PathVariable("lessonId") final Integer lessonId,
        @RequestBody final UpdateLessonRequest lessonDto
    ) {
        return lessonService.update(lessonId, lessonDto);

    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> readLesson(
        @PathVariable("lessonId") final Integer lessonId
    ) {
        return ResponseEntity.ok(
            lessonService.read(lessonId)
        );
    }
}
