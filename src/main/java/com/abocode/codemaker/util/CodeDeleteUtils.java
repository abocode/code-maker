package com.abocode.codemaker.util;

import java.io.File;

public class CodeDeleteUtils
{

    private static String buss_package;
    private static final String src_url;
    private static final String web_url;
    private static final String action_inx = "action";
    private static final String entity_inx = "entity";
    private static final String page_inx = "page";
    private static final String service_inx = "service";
    private static final String impl_inx = "impl";
    private static String action_url;
    private static String entity_url;
    private static String page_url;
    private static String service_url;
    private static String service_impl_url;
    private static String jsp_url;
    private static String jsp_add_url;
    private static String jsp_edit_url;

    public CodeDeleteUtils()
    {
    }

    public static void init(String gn_package, String name)
    {
        action_url = (new StringBuilder(String.valueOf(src_url))).append("/").append("action").append("/").append(gn_package).append("/").append(name).append("Action.java").toString();
        entity_url = (new StringBuilder(String.valueOf(src_url))).append("/").append("entity").append("/").append(gn_package).append("/").append(name).append("Entity.java").toString();
        page_url = (new StringBuilder(String.valueOf(src_url))).append("/").append("page").append("/").append(gn_package).append("/").append(name).append("Page.java").toString();
        service_url = (new StringBuilder(String.valueOf(src_url))).append("/").append("service").append("/").append(gn_package).append("/").append(name).append("ServiceI.java").toString();
        service_impl_url = (new StringBuilder(String.valueOf(src_url))).append("/").append("service").append("/").append("impl").append("/").append(gn_package).append("/").append(name).append("ServiceImpl.java").toString();
        jsp_url = (new StringBuilder(String.valueOf(web_url))).append("/").append(gn_package).append("/").append(name).append(".jsp").toString();
        jsp_add_url = (new StringBuilder(String.valueOf(web_url))).append("/").append(gn_package).append("/").append(name).append("-main-add.jsp").toString();
        jsp_edit_url = (new StringBuilder(String.valueOf(web_url))).append("/").append(gn_package).append("/").append(name).append("-main-edit.jsp").toString();
        String path = getProjectPath();
        action_url = (new StringBuilder(String.valueOf(path))).append("/").append(action_url).toString();
        entity_url = (new StringBuilder(String.valueOf(path))).append("/").append(entity_url).toString();
        page_url = (new StringBuilder(String.valueOf(path))).append("/").append(page_url).toString();
        service_url = (new StringBuilder(String.valueOf(path))).append("/").append(service_url).toString();
        service_impl_url = (new StringBuilder(String.valueOf(path))).append("/").append(service_impl_url).toString();
        jsp_url = (new StringBuilder(String.valueOf(path))).append("/").append(jsp_url).toString();
        jsp_add_url = (new StringBuilder(String.valueOf(path))).append("/").append(jsp_add_url).toString();
        jsp_edit_url = (new StringBuilder(String.valueOf(path))).append("/").append(jsp_edit_url).toString();
    }

    public static void main(String args[])
    {
        String name = "Company";
        String subPackage = "test";
        delCode(subPackage, name);
    }

    public static void delCode(String subPackage, String codeName)
    {
        init(subPackage, codeName);
        delete(action_url);
        delete(entity_url);
        delete(page_url);
        delete(service_url);
        delete(service_impl_url);
        delete(jsp_edit_url);
        delete(jsp_url);
        delete(jsp_add_url);
        System.out.println("--------------------\u5220\u9664\u52A8\u4F5C\u7ED3\u675F-----------------------");
    }

    public static String getProjectPath()
    {
        String path = (new StringBuilder(String.valueOf(System.getProperty("user.dir").replace("\\", "/")))).append("/").toString();
        return path;
    }

    public static boolean delete(String strFileName)
    {
        File fileDelete = new File(strFileName);
        if(!fileDelete.exists() || !fileDelete.isFile())
        {
            return false;
        } else
        {
            System.out.println((new StringBuilder("--------\u6210\u529F\u5220\u9664\u6587\u4EF6---------")).append(strFileName).toString());
            return fileDelete.delete();
        }
    }

    static 
    {
        buss_package = CodeResourceUtils.bussiPackage;
        src_url = (new StringBuilder("src/")).append(buss_package).toString();
        web_url = (new StringBuilder("WebRoot/")).append(buss_package).toString();
    }
}
