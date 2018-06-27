package com.abocode.codemaker.generate.support;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.abocode.codemaker.db.TableLoading;
import com.abocode.codemaker.generate.CallBack;
import com.abocode.codemaker.generate.CodeFactory;
import com.abocode.codemaker.util.CodeDateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.abocode.codemaker.beans.Column;
import com.abocode.codemaker.beans.TemplateProperty;
import com.abocode.codemaker.util.CodeResourceUtils;
import com.abocode.codemaker.util.NonceUtils;
import com.abocode.codemaker.constant.TemplateConstant;

public class JFasterCodeGenerate implements CallBack {
    private static final Log log = LogFactory.getLog(JFasterCodeGenerate.class);
    private static String entityPackage = "test";
    private static String entityName = "Person";
    private static String tableName = "person";
    private static String ftlDescription = "公告";
    private static String primaryKeyPolicy = "uuid";
    private static String sequenceCode = "";
    private List<Column> originalColumns = new ArrayList();
    public static int FIELD_ROW_NUM = 1;
    private static TemplateProperty createFileProperty = new TemplateProperty();
    private List<Column> columns = new ArrayList();
    private TableLoading dbFiledUtil = new TableLoading();

    static {
        createFileProperty.setWebFlag(true);
        createFileProperty.setServiceFlag(true);
        createFileProperty.setJspFlag(true);
        createFileProperty.setServiceImplFlag(true);
        createFileProperty.setJspMode("01");
        createFileProperty.setPageFlag(true);
        createFileProperty.setEntityFlag(true);
    }

    public JFasterCodeGenerate() {
    }

    public JFasterCodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription, TemplateProperty createFileProperty, int fieldRowNum, String primaryKeyPolicy, String sequenceCode) {
        JFasterCodeGenerate.entityName = entityName;
        JFasterCodeGenerate.entityPackage = entityPackage;
        JFasterCodeGenerate.tableName = tableName;
        JFasterCodeGenerate.ftlDescription = ftlDescription;
        JFasterCodeGenerate.createFileProperty = createFileProperty;
        FIELD_ROW_NUM = fieldRowNum;
        JFasterCodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
        JFasterCodeGenerate.sequenceCode = sequenceCode;
    }

    public Map<String, Object> execute() {
        HashMap data = new HashMap();
        data.put("bussiPackage", CodeResourceUtils.bussiPackage);
        data.put("entityPackage", entityPackage);
        data.put("entityName", entityName);
        data.put("tableName", tableName);
        data.put("ftl_description", ftlDescription);
        data.put(TemplateConstant.TABLE_ID, CodeResourceUtils.GENERATE_TABLE_ID);
        data.put(TemplateConstant.PRIMARY_KEY_POLICY, primaryKeyPolicy);
        data.put(TemplateConstant.SEQUENCE_CODE, sequenceCode);
        data.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));
        data.put(TemplateConstant.FIELD_REQUIRED_NAME, Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtils.UI_FIELD_REQUIRED_NUM)?Integer.parseInt(CodeResourceUtils.UI_FIELD_REQUIRED_NUM):-1));
        data.put(TemplateConstant.SEARCH_FIELD_NUM, Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtils.UI_FIELD_SEARCH_NUM)?Integer.parseInt(CodeResourceUtils.UI_FIELD_SEARCH_NUM):-1));
        data.put(TemplateConstant.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));

        try {
            this.columns = this.dbFiledUtil.readTableColumn(tableName);
            data.put("columns", this.columns);
            this.originalColumns = this.dbFiledUtil.readOriginalTableColumn(tableName);
            data.put("originalColumns", this.originalColumns);
            Iterator var3 = this.originalColumns.iterator();

            while(var3.hasNext()) {
                Column serialVersionUID = (Column)var3.next();
                if(serialVersionUID.getFieldName().toLowerCase().equals(CodeResourceUtils.GENERATE_TABLE_ID.toLowerCase())) {
                    data.put("primary_key_type", serialVersionUID.getFieldType());
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            System.exit(-1);
        }

        long serialVersionUID1 = NonceUtils.randomLong() + NonceUtils.currentMills();
        data.put("serialVersionUID", String.valueOf(serialVersionUID1));
        return data;
    }

    public void generateToFile() {
        log.info("----jeecg---Code----Generation----[单表模型:" + tableName + "]------- 生成中。。。");
        CodeFactory codeFactory = new JFasterCodeFactory();
        codeFactory.setCallBack(new JFasterCodeGenerate());
        if(createFileProperty.isJspFlag()) {
            if("03".equals(createFileProperty.getJspMode())) {
                codeFactory.invoke("onetomany/jspSubTemplate.ftl", "jspList");
            } else {
                if("01".equals(createFileProperty.getJspMode())) {
                    codeFactory.invoke("jspTableTemplate.ftl", "jsp");
                }

                if("02".equals(createFileProperty.getJspMode())) {
                    codeFactory.invoke("jspDivTemplate.ftl", "jsp");
                }

                codeFactory.invoke("jspListTemplate.ftl", "jspList");
            }
        }

        if(createFileProperty.isServiceImplFlag()) {
            codeFactory.invoke("serviceImplTemplate.ftl", "serviceImpl");
        }

        if(createFileProperty.isServiceFlag()) {
            codeFactory.invoke("serviceTemplate.ftl", "service");
        }

        if(createFileProperty.isWebFlag()) {
            codeFactory.invoke("controllerTemplate.ftl", "controller");
        }

        if(createFileProperty.isEntityFlag()) {
            codeFactory.invoke("entityTemplate.ftl", "entity");
        }
        codeFactory.invoke("repositoryImplTemplate.ftl", "repositoryImpl");
        codeFactory.invoke("repositoryTemplate.ftl", "repository");

        log.info("----jeecg----Code----Generation-----[单表模型：" + tableName + "]------ 生成完成。。。");
    }

    public static void main(String[] args) {
        System.out.println("----jeecg--------- Code------------- Generation -----[单表模型]------- 生成中。。。");
        (new JFasterCodeGenerate()).generateToFile();
        System.out.println("----jeecg--------- Code------------- Generation -----[单表模型]------- 生成完成。。。");
    }
}
