package com.monari.monariback.lesson.repository;

import com.monari.monariback.lesson.entity.Lesson;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCustomRepository {

    List<Lesson> findLessonsByPageSize(final Integer pageSize, final Integer pageNum);

    List<Lesson> searchLessons(final String keyword, final Integer pageSize, final Integer pageNum);

    int getTotalLessonPages(final int pageSize, final String keyword);

    long getTotalLessonCount(final String keyword);

}
