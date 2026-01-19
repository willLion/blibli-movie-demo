package com.blibli.utils;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class MapperAnnotationPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true; // 插件验证通过
    }

    // 为Mapper接口添加@Mapper注解
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        // 添加@Mapper注解
        FullyQualifiedJavaType mapperAnnotation = new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper");
        interfaze.addAnnotation("@Mapper");
        interfaze.addImportedType(mapperAnnotation);
        return true;
    }
}