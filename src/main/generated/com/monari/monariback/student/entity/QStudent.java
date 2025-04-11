package com.monari.monariback.student.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = -1875456791L;

    public static final QStudent student = new QStudent("student");

    public final com.monari.monariback.common.entity.QBaseEntity _super = new com.monari.monariback.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.monari.monariback.common.enumerated.Grade> grade = createEnum("grade", com.monari.monariback.common.enumerated.Grade.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final ComparablePath<java.util.UUID> publicId = createComparable("publicId", java.util.UUID.class);

    public final EnumPath<com.monari.monariback.common.enumerated.SchoolLevel> schoolLevel = createEnum("schoolLevel", com.monari.monariback.common.enumerated.SchoolLevel.class);

    public final StringPath socialId = createString("socialId");

    public final EnumPath<com.monari.monariback.common.enumerated.SocialProvider> socialProvider = createEnum("socialProvider", com.monari.monariback.common.enumerated.SocialProvider.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStudent(String variable) {
        super(Student.class, forVariable(variable));
    }

    public QStudent(Path<? extends Student> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudent(PathMetadata metadata) {
        super(Student.class, metadata);
    }

}

