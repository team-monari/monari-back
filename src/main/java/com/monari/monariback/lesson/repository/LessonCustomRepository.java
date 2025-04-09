package com.monari.monariback.lesson.repository;

import com.monari.monariback.lesson.entity.Lesson;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCustomRepository {

    List<Lesson> findLessonsByPageSize(Integer pageSize, Integer pageNum);

    List<Lesson> searchLessons(String keyword, Integer pageSize, Integer pageNum);

    int getTotalLessonPages(int pageSize);

}
