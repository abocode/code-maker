package com.abocode.codemaker.generate;

/**
 * Created by Franky Guan on 2017/5/17.
 */
public enum CodeType {
    serviceImpl("ServiceImpl"),
    dao("Dao"),
    service("Service"),
    controller("Controller"),
    page("Page"),
    entity("Entity"),
    jsp(""),
    jsp_add(""),
    jsp_update(""),
    js(""),
    jsList("List"),
    jspList("List"),
    jspSubList("SubList");

    private String type;

    private CodeType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
