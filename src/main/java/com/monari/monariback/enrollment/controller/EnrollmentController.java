package com.monari.monariback.enrollment.controller;


import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyStudent;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.enrollment.dto.request.EnrollmentCreateRequest;
import com.monari.monariback.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @OnlyStudent
    @PostMapping()
    public ResponseEntity<String> enrollment(
        @RequestBody final EnrollmentCreateRequest enrollmentCreateRequest,
        @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(
            enrollmentService.enroll(enrollmentCreateRequest, accessor)
        );
    }
}
