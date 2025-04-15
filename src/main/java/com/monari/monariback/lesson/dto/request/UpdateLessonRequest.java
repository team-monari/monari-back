package com.monari.monariback.lesson.dto.request;

import static com.monari.monariback.lesson.constant.LessonValidationConstants.AMOUNT_POSITIVE_OR_ZERO;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.AMOUNT_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.DEADLINE_FUTURE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.DEADLINE_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.DESCRIPTION_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.DESCRIPTION_SIZE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.END_DATE_FUTURE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.END_DATE_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.LOCATION_ID_POSITIVE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.LOCATION_ID_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.MAX_STUDENT_MIN;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.MAX_STUDENT_MIN_VALUE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.MAX_STUDENT_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.MIN_STUDENT_MIN;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.MIN_STUDENT_MIN_VALUE;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.MIN_STUDENT_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.REGION_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.SCHOOL_LEVEL_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.START_DATE_FUTURE_OR_PRESENT;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.START_DATE_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.STATUS_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.SUBJECT_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.TITLE_MAX_LENGTH;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.TITLE_REQUIRED;
import static com.monari.monariback.lesson.constant.LessonValidationConstants.TITLE_SIZE;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.lesson.entity.enurmurated.LessonStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UpdateLessonRequest(

    @NotNull(message = LOCATION_ID_REQUIRED)
    @Positive(message = LOCATION_ID_POSITIVE)
    Integer locationId,

    @NotBlank(message = TITLE_REQUIRED)
    @Size(max = TITLE_MAX_LENGTH, message = TITLE_SIZE)
    String title,

    @NotBlank(message = DESCRIPTION_REQUIRED)
    @Size(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_SIZE)
    String description,

    @NotNull(message = AMOUNT_REQUIRED)
    @PositiveOrZero(message = AMOUNT_POSITIVE_OR_ZERO)
    Integer amount,

    @NotNull(message = MIN_STUDENT_REQUIRED)
    @Min(value = MIN_STUDENT_MIN_VALUE, message = MIN_STUDENT_MIN)
    Integer minStudent,

    @NotNull(message = MAX_STUDENT_REQUIRED)
    @Min(value = MAX_STUDENT_MIN_VALUE, message = MAX_STUDENT_MIN)
    Integer maxStudent,

    @NotNull(message = START_DATE_REQUIRED)
    @FutureOrPresent(message = START_DATE_FUTURE_OR_PRESENT)
    LocalDate startDate,

    @NotNull(message = END_DATE_REQUIRED)
    @Future(message = END_DATE_FUTURE)
    LocalDate endDate,

    @NotNull(message = DEADLINE_REQUIRED)
    @Future(message = DEADLINE_FUTURE)
    LocalDate deadline,

    @NotNull(message = STATUS_REQUIRED)
    LessonStatus status,
    @NotNull(message = REGION_REQUIRED)
    Region region,

    @NotNull(message = SCHOOL_LEVEL_REQUIRED)
    SchoolLevel schoolLevel,

    @NotNull(message = SUBJECT_REQUIRED)
    Subject subject

) {

}
