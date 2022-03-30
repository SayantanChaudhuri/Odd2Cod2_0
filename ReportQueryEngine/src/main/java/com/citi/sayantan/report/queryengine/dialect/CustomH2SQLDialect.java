package com.citi.sayantan.report.queryengine.dialect;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import org.hibernate.dialect.H2Dialect;

import java.sql.Types;

public class CustomH2SQLDialect extends H2Dialect {

    public CustomH2SQLDialect() {
        super();
        registerHibernateType(Types.OTHER, StringArrayType.class.getName());
        registerHibernateType(Types.OTHER, IntArrayType.class.getName());
//        registerHibernateType(Types.OTHER, type.class.getName());
//        registerHibernateType(Types.OTHER, JsonBinaryType.class.getName());
        registerHibernateType(Types.OTHER, JsonNodeBinaryType.class.getName());
        registerHibernateType(Types.OTHER, JsonNodeStringType.class.getName());
    }
}