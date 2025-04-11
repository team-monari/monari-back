package com.monari.monariback.lesson.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLesson is a Querydsl query type for Lesson
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLesson extends EntityPathBase<Lesson> {

    private static final long serialVersionUID = -1003307309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLesson lesson = new QLesson("lesson");

    public final com.monari.monariback.common.entity.QBaseEntity _super = new com.monari.monariback.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> deadline = createDate("deadline", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.monari.monariback.location.entity.QLocation location;

    public final NumberPath<Integer> maxStudent = createNumber("maxStudent", Integer.class);

    public final NumberPath<Integer> minStudent = createNumber("minStudent", Integer.class);

    public final EnumPath<com.monari.monariback.common.enumerated.SchoolLevel> schoolLevel = createEnum("schoolLevel", com.monari.monariback.common.enumerated.SchoolLevel.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<com.monari.monariback.lesson.entity.enurmurated.LessonStatus> status = createEnum("status", com.monari.monariback.lesson.entity.enurmurated.LessonStatus.class);

    public final EnumPath<com.monari.monariback.common.enumerated.Subject> subject = createEnum("subject", com.monari.monariback.common.enumerated.Subject.class);

    public final com.monari.monariback.teacher.entity.QTeacher teacher;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLesson(String variable) {
        this(Lesson.class, forVariable(variable), INITS);
    }

    public QLesson(Path<? extends Lesson> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLesson(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLesson(PathMetadata metadata, PathInits inits) {
        this(Lesson.class, metadata, inits);
    }

    public QLesson(Class<? extends Lesson> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new com.monari.monariback.location.entity.QLocation(forProperty("location")) : null;
        this.teacher = inits.isInitialized("teacher") ? new com.monari.monariback.teacher.entity.QTeacher(forProperty("teacher")) : null;
    }

}

