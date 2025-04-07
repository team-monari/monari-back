package com.monari.monariback.study.service;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.study.dto.request.StudyCreateRequest;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.repository.StudyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@Transactional
class StudyServiceTest {

    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StudyService studyService;

    private Location saveLocation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        Location location = new Location(
                null,
                "요가 클래스",                        // serviceSubcategory
                "OPEN",                            // serviceStatus
                "CREDIT_CARD",                     // paymentMethod
                "서울 강남센터",                      // locationName
                "https://example.com/service/101", // serviceUrl
                LocalDateTime.of(2025, 4, 10, 10, 0, 0, 0),  // registrationStartDateTime
                LocalDateTime.of(2025, 4, 20, 18, 0, 0, 0),  // registrationEndDateTime
                LocalDateTime.of(2025, 4, 10, 10, 0, 0, 0),  // cancellationStartDateTime
                LocalDateTime.of(2025, 4, 19, 23, 59, 59, 0),// cancellationEndDateTime
                "클래스 하루 전까지 무료 취소 가능",      // cancellationPolicyInfo
                1                                   // cancellationDeadline (ex: 1일 전)
        );
        locationRepository.save(location);
        return location;
    }

    @Test
    void 스터디_등록_성공() {
        //given
        String title = "스터디 제목";
        String description = "스터디 설명";
        Subject subject = Subject.MATH;
        SchoolLevel schoolLevel = SchoolLevel.HIGH;
        Location location = saveLocation();

        StudyCreateRequest studyDto = new StudyCreateRequest(title, description, subject, schoolLevel, location.getId());

        //when
        studyService.createStudy(studyDto);

        //then
        Study study = studyRepository.findAll().get(0);
        Assertions.assertThat(study.getTitle()).isEqualTo(title);
        Assertions.assertThat(study.getDescription()).isEqualTo(description);
        Assertions.assertThat(study.getSubject()).isEqualTo(subject);
        Assertions.assertThat(study.getSchoolLevel()).isEqualTo(schoolLevel);
        Assertions.assertThat(study.getLocation()).isEqualTo(location);
    }
}