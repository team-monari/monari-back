package com.monari.monariback.location.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocation is a Querydsl query type for Location
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation extends EntityPathBase<Location> {

    private static final long serialVersionUID = 246461837L;

    public static final QLocation location = new QLocation("location");

    public final NumberPath<Integer> cancellationDeadline = createNumber("cancellationDeadline", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> cancellationEndDateTime = createDateTime("cancellationEndDateTime", java.time.LocalDateTime.class);

    public final StringPath cancellationPolicyInfo = createString("cancellationPolicyInfo");

    public final DateTimePath<java.time.LocalDateTime> cancellationStartDateTime = createDateTime("cancellationStartDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath locationName = createString("locationName");

    public final StringPath paymentMethod = createString("paymentMethod");

    public final DateTimePath<java.time.LocalDateTime> registrationEndDateTime = createDateTime("registrationEndDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> registrationStartDateTime = createDateTime("registrationStartDateTime", java.time.LocalDateTime.class);

    public final StringPath serviceStatus = createString("serviceStatus");

    public final StringPath serviceSubcategory = createString("serviceSubcategory");

    public final StringPath serviceUrl = createString("serviceUrl");

    public QLocation(String variable) {
        super(Location.class, forVariable(variable));
    }

    public QLocation(Path<? extends Location> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation(PathMetadata metadata) {
        super(Location.class, metadata);
    }

}

