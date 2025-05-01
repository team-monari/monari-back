package com.monari.monariback.lesson.repository;

import com.monari.monariback.lesson.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer>, LessonCustomRepository {

    Page<Lesson> findAllByStartDate(final LocalDate lessonStartDate, Pageable pageable);
}
