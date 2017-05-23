package com.abocode.codemaker.util;

import java.util.List;
import org.apache.commons.lang.StringUtils;

public class CodeStringUtils
{

    public CodeStringUtils()
    {
    }

    public static String getStringSplit(String val[])
    {
        StringBuffer sqlStr = new StringBuffer();
        String as[];
        int j = (as = val).length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            if(StringUtils.isNotBlank(s))
            {
                sqlStr.append(",");
                sqlStr.append("'");
                sqlStr.append(s.trim());
                sqlStr.append("'");
            }
        }

        return sqlStr.toString().substring(1);
    }

    public static String getInitialSmall(String str)
    {
        if(StringUtils.isNotBlank(str))
            str = (new StringBuilder(String.valueOf(str.substring(0, 1).toLowerCase()))).append(str.substring(1)).toString();
        return str;
    }

    public static Integer getIntegerNotNull(Integer t)
    {
        if(t == null)
            return Integer.valueOf(0);
        else
            return t;
    }

    public static boolean isIn(String substring, String source[])
    {
        if(source == null || source.length == 0)
            return false;
        for(int i = 0; i < source.length; i++)
        {
            String aSource = source[i];
            if(aSource.equals(substring))
                return true;
        }

        return false;
    }

    public static boolean isIn(String substring, List ls)
    {
        String source[] = new String[0];
        if(ls != null)
            source = (String[])ls.toArray();
        if(source == null || source.length == 0)
            return false;
        for(int i = 0; i < source.length; i++)
        {
            String aSource = source[i];
            if(aSource.equals(substring))
                return true;
        }

        return false;
    }
}
