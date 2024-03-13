package com.toy.mall.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingInfo is a Querydsl query type for ShippingInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingInfo extends BeanPath<ShippingInfo> {

    private static final long serialVersionUID = 2107383977L;

    public static final QShippingInfo shippingInfo = new QShippingInfo("shippingInfo");

    public final StringPath city = createString("city");

    public final StringPath detailedAddress = createString("detailedAddress");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath receiverPhone = createString("receiverPhone");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QShippingInfo(String variable) {
        super(ShippingInfo.class, forVariable(variable));
    }

    public QShippingInfo(Path<? extends ShippingInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingInfo(PathMetadata metadata) {
        super(ShippingInfo.class, metadata);
    }

}

