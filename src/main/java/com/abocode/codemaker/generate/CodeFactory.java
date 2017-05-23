package com.abocode.codemaker.generate;

import java.util.Map;

/**
 * Created by Franky Guan on 2017/5/16.
 */
public interface CodeFactory {
    void generateFile(String templateFileName, String type, Map data);

    String getClassPath();

    String getTemplatePath();

    String getCodePath(String type, String entityPackage, String entityName);

    void invoke(String templateFileName, String type);

    CallBack getCallBack();

    void setCallBack(CallBack callBack);
}
