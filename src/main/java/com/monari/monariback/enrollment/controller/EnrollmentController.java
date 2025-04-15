package com.monari.monariback.enrollment.controller;


import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyStudent;
import com.monari.monariback.auth.aop.OnlyTeacher;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.enrollment.dto.request.EnrollmentCreateRequest;
import com.monari.monariback.enrollment.dto.response.EnrollmentResponse;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.enrollment.service.EnrollmentService;
import java.util.List;
import java.util.UUID;
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

    @OnlyTeacher
    @GetMapping("/{lessonId}")
    public ResponseEntity<List<EnrollmentResponse>> getStudentList(
        @PathVariable(name = "lessonId") final Integer lessonId,
        @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(
            enrollmentService.getStudentList(lessonId)
        );
    }

    @OnlyTeacher
    @PatchMapping("/{lessonId}/{studentId}/{status}")
    public ResponseEntity<EnrollmentResponse> modifyStatus(
        @PathVariable(name = "lessonId") final Integer lessonId,
        @PathVariable(name = "studentId") final UUID studentId,
        @PathVariable(name = "status") final EnrollmentStatus status,
        @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(
            enrollmentService.modifyStatus(studentId, lessonId, status)
        );
    }

    @PatchMapping("/{lessonId}/price")
    public ResponseEntity<String> decideFinalPrice(
        @PathVariable(name = "lessonId") final Integer lessonId
    ) {
        return ResponseEntity.ok(
            enrollmentService.decideFinalPrice(lessonId)
        );
    }
}
