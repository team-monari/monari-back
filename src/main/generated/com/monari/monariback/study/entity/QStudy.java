package com.monari.monariback.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = 1505587369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final com.monari.monariback.common.entity.QBaseEntity _super = new com.monari.monariback.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.monari.monariback.location.entity.QLocation location;

    public final EnumPath<com.monari.monariback.common.enumerated.SchoolLevel> schoolLevel = createEnum("schoolLevel", com.monari.monariback.common.enumerated.SchoolLevel.class);

    public final EnumPath<com.monari.monariback.study.entity.enumerated.StudyStatus> status = createEnum("status", com.monari.monariback.study.entity.enumerated.StudyStatus.class);

    public final EnumPath<com.monari.monariback.common.enumerated.Subject> subject = createEnum("subject", com.monari.monariback.common.enumerated.Subject.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy(PathMetadata metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new com.monari.monariback.location.entity.QLocation(forProperty("location")) : null;
    }

}

