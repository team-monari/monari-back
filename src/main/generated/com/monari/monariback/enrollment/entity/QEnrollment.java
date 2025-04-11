package com.monari.monariback.enrollment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnrollment is a Querydsl query type for Enrollment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEnrollment extends EntityPathBase<Enrollment> {

    private static final long serialVersionUID = -1325598997L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnrollment enrollment = new QEnrollment("enrollment");

    public final com.monari.monariback.common.entity.QBaseEntity _super = new com.monari.monariback.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.monari.monariback.lesson.entity.QLesson lesson;

    public final com.monari.monariback.student.entity.QStudent student;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QEnrollment(String variable) {
        this(Enrollment.class, forVariable(variable), INITS);
    }

    public QEnrollment(Path<? extends Enrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnrollment(PathMetadata metadata, PathInits inits) {
        this(Enrollment.class, metadata, inits);
    }

    public QEnrollment(Class<? extends Enrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lesson = inits.isInitialized("lesson") ? new com.monari.monariback.lesson.entity.QLesson(forProperty("lesson"), inits.get("lesson")) : null;
        this.student = inits.isInitialized("student") ? new com.monari.monariback.student.entity.QStudent(forProperty("student")) : null;
    }

}

