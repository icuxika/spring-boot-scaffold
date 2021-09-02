package com.icuxika.scaffold.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public MyJavaTypeResolver() {
        super();
    }

    @Override
    public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType answer = super.calculateJavaType(introspectedColumn);
        if (introspectedColumn.getJdbcType() == Types.TINYINT) {
            answer = calculateTinyInt(introspectedColumn, answer);
        }
        return answer;
    }

    private FullyQualifiedJavaType calculateTinyInt(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer;
        if (column.getLength() > 1) {
            answer = new FullyQualifiedJavaType(Integer.class.getName());
        } else {
            answer = defaultType;
        }
        return answer;
    }
}
