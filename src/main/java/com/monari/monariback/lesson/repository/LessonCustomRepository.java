package com.monari.monariback.lesson.repository;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.lesson.entity.Lesson;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCustomRepository {

    List<Lesson> findLessonsByPageSize(final Integer pageSize, final Integer pageNum);

    List<Lesson> searchLessons(final String keyword, final Integer pageSize, final Integer pageNum,
        final SchoolLevel schoolLevel, final Subject subject, final Region region);

    int getTotalLessonPages(final int pageSize, final String keyword);

    List<Lesson> findAllByTeacherId(final int teacherId, final int pageSize, final int pageNum);

    long getTotalLessonCount(final String keyword, final SchoolLevel schoolLevel,
        final Subject subject, final Region region);

    Long getTotalLessenByTeacherId(final int teacherId);

}
