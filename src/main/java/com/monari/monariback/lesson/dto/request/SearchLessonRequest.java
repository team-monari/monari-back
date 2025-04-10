package com.monari.monariback.lesson.dto.request;

public record SearchLessonRequest(
    String keyword,
    Integer pageNumber,
    Integer pageSize
) {

}
