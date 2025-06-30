package com.monari.monariback.lesson.repository;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.SearchType;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.dto.response.LessonWithTeacherResponse;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCustomRepository {

    List<LessonResponse> findLessonsWithStudentCountByAuth(
        final Integer pageSize,
        final Integer pageNumber,
        final UUID teacherId,
        final UUID studentId
    );

    int getTotalLessonPages(final int pageSize, final String keyword);


    long getTotalLessonCount(final String keyword, final SchoolLevel schoolLevel,
        final Subject subject, final Region region, final LessonType lessonType,
        final SearchType searchType);

    Long getTotalLessenByTeacherId(final int teacherId);

    Optional<LessonWithTeacherResponse> findByLessonIdWithTeacher(final int locationId);

    Integer countTotalLessons();

    List<LessonResponse> findLessonsWithStudentCount(
        final Integer pageSize,
        final Integer pageNumber
    );

    List<LessonResponse> searchLessonsWithStudentCount(
        final String keyword,
        final Integer pageSize,
        final Integer pageNum,
        final SchoolLevel schoolLevel,
        final Subject subject,
        final Region region,
        final LessonType lessonType,
        final SearchType searchType
    );

    void updateStatus(final List<Integer> lessonIds, final LessonStatus lessonStatus);
}
