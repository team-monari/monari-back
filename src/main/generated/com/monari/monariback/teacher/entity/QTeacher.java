package com.monari.monariback.teacher.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeacher is a Querydsl query type for Teacher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacher extends EntityPathBase<Teacher> {

    private static final long serialVersionUID = 1111714313L;

    public static final QTeacher teacher = new QTeacher("teacher");

    public final com.monari.monariback.common.entity.QBaseEntity _super = new com.monari.monariback.common.entity.QBaseEntity(this);

    public final StringPath career = createString("career");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath major = createString("major");

    public final StringPath name = createString("name");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final ComparablePath<java.util.UUID> publicId = createComparable("publicId", java.util.UUID.class);

    public final StringPath socialId = createString("socialId");

    public final EnumPath<com.monari.monariback.common.enumerated.SocialProvider> socialProvider = createEnum("socialProvider", com.monari.monariback.common.enumerated.SocialProvider.class);

    public final StringPath university = createString("university");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTeacher(String variable) {
        super(Teacher.class, forVariable(variable));
    }

    public QTeacher(Path<? extends Teacher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeacher(PathMetadata metadata) {
        super(Teacher.class, metadata);
    }

}

