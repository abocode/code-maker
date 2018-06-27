package com.abocode.codemaker.generate;

/**
 * Created by Franky Guan on 2017/5/17.
 */
public enum CodeType {
    controller("Controller"),

    service("Service"),
    serviceImpl("ServiceImpl"),

    entity("Entity"),
    repository("Repository"),
    repositoryImpl("RepositoryImpl"),

    page("Page"),
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
