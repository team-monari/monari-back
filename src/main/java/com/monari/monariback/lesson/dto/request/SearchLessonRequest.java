package com.monari.monariback.lesson.dto.request;

import static com.monari.monariback.lesson.constant.LessonValidationConstants.KEYWORD_MIN_LENGTH;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.KEYWORD_SIZE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.PAGE_NUMBER_MIN;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.PAGE_SIZE_MIN;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record SearchLessonRequest(

    @Size(min = KEYWORD_MIN_LENGTH, message = KEYWORD_SIZE)
    String keyword,

    @Min(value = 1, message = PAGE_NUMBER_MIN)
    Integer pageNumber,

    @Min(value = 1, message = PAGE_SIZE_MIN)
    Integer pageSize,

    SchoolLevel schoolLevel,
    Subject subject
) {

}
