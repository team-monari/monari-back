package com.monari.monariback.enrollment.controller;


import com.monari.monariback.enrollment.dto.EnrollmentRequest;
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

    // TODO : Jwt 적용
    @PostMapping()
    public ResponseEntity<Integer> enrollment(
        @RequestBody final EnrollmentRequest enrollmentRequest) {
        return ResponseEntity.ok(
            enrollmentService.enrollment(enrollmentRequest)
        );
    }
}
