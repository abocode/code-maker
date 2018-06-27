package com.abocode.codemaker.util;

import java.util.ResourceBundle;

public class CodeResourceUtils
{

    private static final ResourceBundle bundle = ResourceBundle.getBundle("maker-config/database");
    private static final ResourceBundle bundlePath = ResourceBundle.getBundle("maker-config/contextConfig");
    public static String DIVER_NAME = "com.mysql.jdbc.Driver";
    public static String URL;
    public static String USERNAME = "root";
    public static String PASSWORD = "root";
    public static String DATABASE_NAME = "sys";
    public static String DATABASE_TYPE = "mysql";
    public static String UI_FIELD_REQUIRED_NUM = "4";
    public static String UI_FIELD_SEARCH_NUM = "3";
    public static String project_path;
    public static String web_root_package;
    public static String source_root_package;
    public static String bussiPackage;
    public static String entity_package;
    public static String page_package;
    public static boolean FILED_CONVERT = true;
    public static String FREEMARKER_CLASSPATH = "/maker-config/template";
    public static String PACKAGE_SERVICE_STYLE = "service";
    public static String PACKAGE_PROJECT_STYLE = "project";
    public static String ENTITY_URL;
    public static String PAGE_URL;
    public static String ENTITY_URL_INX;
    public static String PAGE_URL_INX;
    public static String TEMPLATEPATH = getTEMPLATEPATH();
    public static String CODEPATH;
    public static String JSPPATH;
    public static String GENERATE_TABLE_ID = getGenerate_table_id();
    public static String GENERATE_UI_FILTER_FIELDS = getGenerate_ui_filter_fields();
    public static String SYSTEM_ENCODING = getSYSTEM_ENCODING();

    public CodeResourceUtils()
    {
    }

    private void ResourceUtil()
    {
    }

    public static final String getDIVER_NAME()
    {
        return bundle.getString("diver_name");
    }

    public static final String getURL()
    {
        return bundle.getString("url");
    }

    public static final String getUSERNAME()
    {
        return bundle.getString("username");
    }

    public static final String getPASSWORD()
    {
        return bundle.getString("password");
    }

    public static final String getDATABASE_NAME()
    {
        return bundle.getString("database_name");
    }

    public static final boolean getFILED_CONVERT()
    {
        String s = bundlePath.getString("filed_convert");
        return !s.toString().equals("false");
    }

    private static String getBussiPackage()
    {
        return bundlePath.getString("biz_package");
    }

    public static final String getEntityPackage()
    {
        return bundlePath.getString("entity_package");
    }

    public static final String getPagePackage()
    {
        return bundlePath.getString("page_package");
    }

    public static final String getTEMPLATEPATH()
    {
        return bundlePath.getString("templatepath");
    }

    public static final String getSourceRootPackage()
    {
        return bundlePath.getString("source_root_package");
    }

    public static final String getWebRootPackage()
    {
        return bundlePath.getString("webroot_package");
    }

    public static final String getSYSTEM_ENCODING()
    {
        return bundlePath.getString("system_encoding");
    }

    public static final String getGenerate_table_id()
    {
        return bundlePath.getString("generate_table_id");
    }

    public static final String getGenerate_ui_filter_fields()
    {
        return bundlePath.getString("ui_filter_fields");
    }

    public static final String getUi_search_filed_num()
    {
        return bundlePath.getString("ui_search_filed_num");
    }

    public static final String getUi_field_required_num()
    {
        return bundlePath.getString("ui_field_required_num");
    }

    public static String getProjectPath()
    {
        String projp = bundlePath.getString("project_path");
        if(projp != null && !"".equals(projp))
            project_path = projp;
        return project_path;
    }

    public static void setProject_path(String project_path)
    {
        project_path = project_path;
    }

    static
    {
        DIVER_NAME = getDIVER_NAME();
        URL = getURL();
        USERNAME = getUSERNAME();
        PASSWORD = getPASSWORD();
        DATABASE_NAME = getDATABASE_NAME();
        FILED_CONVERT = getFILED_CONVERT();
        source_root_package = getSourceRootPackage();
        web_root_package = getWebRootPackage();
        bussiPackage = getBussiPackage();
        UI_FIELD_SEARCH_NUM = getUi_search_filed_num();
        if(URL.indexOf("mysql") >= 0 || URL.indexOf("MYSQL") >= 0)
            DATABASE_TYPE = "mysql";
        else
        if(URL.indexOf("oracle") >= 0 || URL.indexOf("ORACLE") >= 0)
            DATABASE_TYPE = "oracle";
        else
        if(URL.indexOf("postgresql") >= 0 || URL.indexOf("POSTGRESQL") >= 0)
            DATABASE_TYPE = "postgresql";
        else
        if(URL.indexOf("sqlserver") >= 0 || URL.indexOf("sqlserver") >= 0)
            DATABASE_TYPE = "sqlserver";
        source_root_package = source_root_package.replace(".", "/");
        web_root_package = web_root_package.replace(".", "/");
        String bizPackageUrl = bussiPackage.replace(".", "/");
        //修改实体文件的路径
        ENTITY_URL = (new StringBuilder(String.valueOf(source_root_package))).append("/").append(bizPackageUrl).append("/").append(entity_package).append("/").toString();
        PAGE_URL = (new StringBuilder(String.valueOf(source_root_package))).append("/").append(bizPackageUrl).append("/").append(page_package).append("/").toString();
        ENTITY_URL_INX = (new StringBuilder(String.valueOf(bussiPackage))).append(".").append(entity_package).append(".").toString();
        PAGE_URL_INX = (new StringBuilder(String.valueOf(bussiPackage))).append(".").append(page_package).append(".").toString();
        CODEPATH = (new StringBuilder(String.valueOf(source_root_package))).append("/").append(bizPackageUrl).append("/").toString();
        JSPPATH = (new StringBuilder(String.valueOf(web_root_package))).append("/").append("webpage").append("/").toString();
    }
}
