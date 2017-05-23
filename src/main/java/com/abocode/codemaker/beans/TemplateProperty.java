package com.abocode.codemaker.beans;


public class TemplateProperty
{

    private boolean actionFlag;
    private boolean serviceFlag;
    private boolean entityFlag;
    private boolean pageFlag;
    private boolean serviceImplFlag;
    private boolean jspFlag;
    private String jspMode;

    public TemplateProperty()
    {
    }

    public boolean isActionFlag()
    {
        return actionFlag;
    }

    public boolean isServiceFlag()
    {
        return serviceFlag;
    }

    public boolean isEntityFlag()
    {
        return entityFlag;
    }

    public boolean isPageFlag()
    {
        return pageFlag;
    }

    public boolean isServiceImplFlag()
    {
        return serviceImplFlag;
    }

    public void setActionFlag(boolean actionFlag)
    {
        this.actionFlag = actionFlag;
    }

    public void setServiceFlag(boolean serviceFlag)
    {
        this.serviceFlag = serviceFlag;
    }

    public void setEntityFlag(boolean entityFlag)
    {
        this.entityFlag = entityFlag;
    }

    public void setPageFlag(boolean pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public void setServiceImplFlag(boolean serviceImplFlag)
    {
        this.serviceImplFlag = serviceImplFlag;
    }

    public boolean isJspFlag()
    {
        return jspFlag;
    }

    public void setJspFlag(boolean jspFlag)
    {
        this.jspFlag = jspFlag;
    }

    public String getJspMode()
    {
        return jspMode;
    }

    public void setJspMode(String jspMode)
    {
        this.jspMode = jspMode;
    }
}
