package com.monari.monariback.lesson.dto.request;

import static com.monari.monariback.lesson.constant.LessonValidationConstants.PAGE_NUMBER_MIN;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.PAGE_SIZE_MIN;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import jakarta.validation.constraints.Min;

public record SearchLessonRequest(

    String keyword,

    @Min(value = 1, message = PAGE_NUMBER_MIN)
    Integer pageNumber,

    @Min(value = 1, message = PAGE_SIZE_MIN)
    Integer pageSize,

    SchoolLevel schoolLevel,
    Subject subject,

    Region region

) {

}
