package com.abocode.codemaker.beans;


import lombok.Data;

@Data
public class TemplateProperty
{
    //web
    private boolean webFlag;
    //application
    private boolean serviceFlag;
    private boolean serviceImplFlag;
    //domain
    private boolean entityFlag;
    private boolean repositoryFlag;
    private boolean repositoryImplFlag;
    //view
    private boolean pageFlag;
    private boolean jspFlag;
    private String jspMode;

    public void buildDefault() {
        this.setJspMode("01");
        this.setJspMode("02");
        this.setWebFlag(true);
        this.setJspFlag(true);
        this.setServiceFlag(true);
        this.setServiceImplFlag(true);
        this.setEntityFlag(true);
    }
}
