package com.masdmtr.skillsmonster.config;

import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

/**
 * Created by dmaslov on 13/07/17.
 */
public class CustomPostgreSqlDialect extends PostgreSQL94Dialect {

    public CustomPostgreSqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
