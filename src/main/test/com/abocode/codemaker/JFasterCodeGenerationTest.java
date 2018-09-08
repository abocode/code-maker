package com.abocode.codemaker;

import com.abocode.codemaker.beans.TemplateProperty;
import com.abocode.codemaker.generate.support.JFasterCodeGenerate;
import org.junit.jupiter.api.Test;

public class JFasterCodeGenerationTest {
    @Test
    public void generateCode() {
        String entityPackage = "maker";
        String entityName = "Test";
        String tableName = "t_s_test";
        String ftlDescription = "abocode代码生成器测试样列";
        int fieldRowNum = 1;
        String primaryKeyPolicy = "uuid";
        String sequenceCode = "";
        TemplateProperty createFileProperty = new TemplateProperty();
        createFileProperty.buildDefault();
        (new JFasterCodeGenerate(entityPackage, entityName, tableName, ftlDescription, createFileProperty, fieldRowNum, primaryKeyPolicy, sequenceCode)
        ).generateToFile();
    }
}
