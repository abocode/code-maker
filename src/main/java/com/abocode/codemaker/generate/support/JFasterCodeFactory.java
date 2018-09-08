package com.abocode.codemaker.generate.support;

import com.abocode.codemaker.generate.*;
import com.abocode.codemaker.util.CodeResourceUtils;
import com.abocode.codemaker.util.CodeStringUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class JFasterCodeFactory extends BaseCodeFactory implements CodeFactory {
    private CallBack callBack;

    public JFasterCodeFactory() {
    }
    public void generateFile(String templateFileName, String type, Map data) {
        try {
            String packageName = data.get("entityPackage").toString();
            String entityName = data.get("entityName").toString();
            String fileNamePath = this.getCodePath(type, packageName, entityName);
            String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
            Template template = this.getConfiguration().getTemplate(templateFileName);
            FileUtils.forceMkdir(new File(fileDir + "/"));
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileNamePath), CodeResourceUtils.SYSTEM_ENCODING);
            template.process(data, out);
            out.close();
        } catch (TemplateException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

    }



    public String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        return path;
    }

    public String getTemplatePath() {
        String path = this.getClassPath() + CodeResourceUtils.TEMPLATEPATH;
        return path;
    }

    public String getCodePath(String type, String entityPackage, String entityName) {
        String path = CodeResourceUtils.getProjectPath();
        StringBuilder str = new StringBuilder();
        if(!StringUtils.isNotBlank(type)) {
            throw new IllegalArgumentException("type is null");
        } else {
            String codeType = (Enum.valueOf(CodeType.class, type)).getValue();
            str.append(path);
            if("jsp".equals(type) || "jspList".equals(type)) {
                str.append( Structure.project.getValue()+CodeResourceUtils.JSPPATH).append(StringUtils.lowerCase(entityPackage));
            }
            if("Controller".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(Structure.project.getValue()+CodeResourceUtils.buildMavenPackage(entityPackage)+"interfaces/web"));
            } else if("ServiceImpl".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(Structure.service.getValue()+CodeResourceUtils.buildMavenPackage(entityPackage)+"application/service"));
            } else if("Service".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(Structure.service.getValue()+CodeResourceUtils.buildMavenPackage(entityPackage)+"application"));
            } else if("RepositoryImpl".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(Structure.service.getValue()+CodeResourceUtils.buildMavenPackage(entityPackage)+"domain/repository/persistence/hibernate"));
            } else if("Repository".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(Structure.service.getValue()+CodeResourceUtils.buildMavenPackage(entityPackage)+"domain/repository"));
            } else if("Entity".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(Structure.service.getValue()+CodeResourceUtils.buildMavenPackage(entityPackage)+"domain/"+codeType));
            }else if(!"List".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(codeType));
            }
            str.append("/");
            if(!"jsp".equals(type) && !"jspList".equals(type)) {
                str.append(StringUtils.capitalize(entityName));
                if(!"Entity".equalsIgnoreCase(codeType)){
                    str.append(codeType);
                }
                str.append(".java");
            } else {
                String jspName = StringUtils.capitalize(entityName);
                str.append(CodeStringUtils.getInitialSmall(jspName));
                str.append(codeType);
                str.append(".jsp");
            }
            return str.toString();
        }
    }

    public void invoke(String templateFileName, String type) {
        new HashMap();
        Map data = this.callBack.execute();
        this.generateFile(templateFileName, type, data);
    }

    public CallBack getCallBack() {
        return this.callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

}
