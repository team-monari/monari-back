package com.monari.monariback.lesson.controller;

import com.monari.monariback.lesson.dto.request.CreateLessonRequest;
import com.monari.monariback.lesson.dto.request.UpdateLessonRequest;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.dto.response.PageInfoResponse;
import com.monari.monariback.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping()
    public ResponseEntity<String> createLesson(
        @RequestBody final CreateLessonRequest lessonDto
    ) {
        return lessonService.create(lessonDto);
    }

    @PatchMapping("/{lessonId}")
    public ResponseEntity<String> updateLesson(
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

    @GetMapping()
    public ResponseEntity<Page<LessonResponse>> readLessons(
        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "6") final Integer pageSize
    ) {
        return ResponseEntity.ok(
            lessonService.readLessons(pageNumber, pageSize)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<Page<LessonResponse>> searchLessons(
        @RequestParam(name = "keyword") final String keyword,
        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "6") final Integer pageSize
    ) {
        return ResponseEntity.ok(
            lessonService.searchLessons(keyword, pageNumber, pageSize)
        );
    }

    @GetMapping("/pages")
    public ResponseEntity<PageInfoResponse> getTotalPages() {
        return ResponseEntity.ok(
            lessonService.getTotalPages()
        );
    }
}
