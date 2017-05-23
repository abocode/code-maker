package com.abocode.codemaker.beans;

import org.apache.commons.lang.StringUtils;

public class DBConvert
{

    public DBConvert()
    {
    }

    public static String getNullAble(String nullable)
    {
        if("YES".equals(nullable) || "yes".equals(nullable) || "y".equals(nullable) || "Y".equals(nullable) || "f".equals(nullable))
            return "Y";
        if("NO".equals(nullable) || "N".equals(nullable) || "no".equals(nullable) || "n".equals(nullable) || "t".equals(nullable))
            return "N";
        else
            return null;
    }

    public static String getNullString(String nullable)
    {
        if(StringUtils.isBlank(nullable))
            return "";
        else
            return nullable;
    }

    public static String getV(String s)
    {
        return (new StringBuilder("'")).append(s).append("'").toString();
    }
}
