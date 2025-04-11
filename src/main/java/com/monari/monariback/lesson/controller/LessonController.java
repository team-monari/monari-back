package com.monari.monariback.lesson.controller;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyTeacher;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.lesson.dto.request.CreateLessonRequest;
import com.monari.monariback.lesson.dto.request.SearchLessonRequest;
import com.monari.monariback.lesson.dto.request.UpdateLessonRequest;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.dto.response.PageInfoResponse;
import com.monari.monariback.lesson.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @OnlyTeacher
    @PostMapping()
    public ResponseEntity<String> createLesson(
        @RequestBody @Valid final CreateLessonRequest lessonDto,
        @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(
            lessonService.createLesson(lessonDto, accessor)
        );
    }

    @OnlyTeacher
    @PatchMapping("/{lessonId}")
    public ResponseEntity<String> updateLesson(
        @PathVariable("lessonId") final Integer lessonId,
        @RequestBody @Valid final UpdateLessonRequest lessonDto,
        @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(
            lessonService.updateLesson(lessonId, lessonDto, accessor)
        );
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> readLesson(
        @PathVariable("lessonId") final Integer lessonId
    ) {
        return ResponseEntity.ok(
            lessonService.readLesson(lessonId)
        );
    }

    @GetMapping()
    public ResponseEntity<Page<LessonResponse>> readLessons(
        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "6") final Integer pageSize
    ) {
        return ResponseEntity.ok(
            lessonService.readLessons(pageNumber, pageSize)
        );
    }

    @GetMapping("/me")
    @OnlyTeacher
    public ResponseEntity<Page<LessonResponse>> readMyLessons(
        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "6") final Integer pageSize,
        @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(
            lessonService.getMyEnrolledLessons(pageNumber, pageSize, accessor));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<LessonResponse>> searchLessons(
        @Valid final SearchLessonRequest searchLessonRequest
    ) {
        return ResponseEntity.ok(
            lessonService.searchLessons(searchLessonRequest)
        );
    }

    @GetMapping("/pages")
    public ResponseEntity<PageInfoResponse> getTotalPages() {
        return ResponseEntity.ok(
            lessonService.getTotalPages()
        );
    }
}
