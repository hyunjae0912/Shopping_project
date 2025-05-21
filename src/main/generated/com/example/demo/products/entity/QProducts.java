package com.example.demo.products.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProducts is a Querydsl query type for Products
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProducts extends EntityPathBase<Products> {

    private static final long serialVersionUID = 1377835039L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProducts products = new QProducts("products");

    public final StringPath desImg = createString("desImg");

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> productid = createNumber("productid", Integer.class);

    public final com.example.demo.user.entity.QUser user;

    public QProducts(String variable) {
        this(Products.class, forVariable(variable), INITS);
    }

    public QProducts(Path<? extends Products> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProducts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProducts(PathMetadata metadata, PathInits inits) {
        this(Products.class, metadata, inits);
    }

    public QProducts(Class<? extends Products> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.demo.user.entity.QUser(forProperty("user")) : null;
    }

}

