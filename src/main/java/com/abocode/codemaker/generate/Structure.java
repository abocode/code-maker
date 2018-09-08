package com.abocode.codemaker.generate;

public enum Structure {
    api("jfaster-api/"),
    service("jfaster-service/"),
    project("jfaster-project/"),
    core("jfaster-core/");

    private String name;
    private Structure(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
