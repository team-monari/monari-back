package com.monari.monariback.lesson.repository;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.SearchType;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.entity.enurmerated.LessonType;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCustomRepository {

    List<Lesson> findLessonsByPageSize(final Integer pageSize, final Integer pageNum);

    List<Lesson> searchLessons(final String keyword, final Integer pageSize, final Integer pageNum,
        final SchoolLevel schoolLevel, final Subject subject, final Region region,
        final LessonType lessonType, final SearchType searchType);

    int getTotalLessonPages(final int pageSize, final String keyword);

    List<Lesson> findAllByTeacherId(final int teacherId, final int pageSize, final int pageNum);

    long getTotalLessonCount(final String keyword, final SchoolLevel schoolLevel,
        final Subject subject, final Region region, final LessonType lessonType,
        final SearchType searchType);

    Long getTotalLessenByTeacherId(final int teacherId);

    Optional<Lesson> findByLessonIdWithTeacher(final int locationId);
}
