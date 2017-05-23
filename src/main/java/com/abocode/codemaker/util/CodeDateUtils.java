package com.abocode.codemaker.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class CodeDateUtils
{

    public static final String DATESTYLE = "yyyyMMddHHmmss";
    public static final String DATESTYLE_EX = "yyyy-MM-dd_HH-mm-ss";
    public static final String DATESTYLE_ = "yyyy-MM-dd";
    public static final String DATESTYLE_YEAR_MONTH = "yyyyMM";
    public static final String DATESTYLE_SHORT = "yyyyMMdd";
    public static final String DATESTYLE_SHORT_EX = "yyyy/MM/dd";
    public static final String DATESTYLE_YEAR_MONTH_EX = "yyyy/MM";
    public static final String DATESTYLE_DETAIL = "yyyyMMddHHmmssSSS";
    public static String dateToString(Date date)
    {
        if(date == null)
            return "";
        else
            return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToStringShort(Date date)
    {
        if(date == null)
            return "";
        else
            return FormatDate(date, "yyyy-MM-dd");
    }

    public static long diffTwoDate(Date date1, Date date2)
    {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        return l1 - l2;
    }

    public static int diffTwoDateDay(Date date1, Date date2)
    {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        int diff = Integer.parseInt((new StringBuilder()).append((l1 - l2) / 3600L / 24L / 1000L).toString());
        return diff;
    }

    public static String FormatDate(Date date, String sf)
    {
        if(date == null)
        {
            return "";
        } else
        {
            SimpleDateFormat dateformat = new SimpleDateFormat(sf);
            return dateformat.format(date);
        }
    }

    public static String getCurrDate()
    {
        Date date_time = new Date();
        return FormatDate(date_time, "yyyy-MM-dd");
    }

    public static Date getCurrDateTime()
    {
        return new Date(System.currentTimeMillis());
    }

    public static String getCurrTime()
    {
        Date date_time = new Date();
        return FormatDate(date_time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDate10to8(String str)
    {
        String y = str.substring(0, 4);
        String m = str.substring(5, 7);
        String d = str.substring(8, 10);
        return (new StringBuilder(String.valueOf(y))).append(m).append(d).toString();
    }

    public static String getDate8to10(String str)
    {
        String y = str.substring(0, 4);
        String m = str.substring(4, 6);
        String d = str.substring(6, 8);
        return (new StringBuilder(String.valueOf(y))).append("-").append(m).append("-").append(d).toString();
    }

    public static String getDay(Date date)
    {
        return FormatDate(date, "dd");
    }

    public static String getHour(Date date)
    {
        return FormatDate(date, "HH");
    }

    public static String getMinute(Date date)
    {
        return FormatDate(date, "mm");
    }

    public static String getMonth(Date date)
    {
        return FormatDate(date, "MM");
    }

    public static int getMonth(Date start, Date end)
    {
        if(start.after(end))
        {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(5, 1);
        int year = endCalendar.get(1) - startCalendar.get(1);
        int month = endCalendar.get(2) - startCalendar.get(2);
        if(startCalendar.get(5) == 1 && temp.get(5) == 1)
            return year * 12 + month + 1;
        if(startCalendar.get(5) != 1 && temp.get(5) == 1)
            return year * 12 + month;
        if(startCalendar.get(5) == 1 && temp.get(5) != 1)
            return year * 12 + month;
        else
            return (year * 12 + month) - 1 >= 0 ? year * 12 + month : 0;
    }

    public static String getSecond(Date date)
    {
        return FormatDate(date, "ss");
    }

    public static String getTime(String year, String month)
    {
        String time = "";
        int len = 31;
        int iYear = Integer.parseInt(year);
        int iMonth = Integer.parseInt(month);
        if(iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11)
            len = 30;
        if(iMonth == 2)
        {
            len = 28;
            if(iYear % 4 == 0 && iYear % 100 == 0 && iYear % 400 == 0 || iYear % 4 == 0 && iYear % 100 != 0)
                len = 29;
        }
        time = (new StringBuilder(String.valueOf(year))).append("-").append(month).append("-").append(String.valueOf(len)).toString();
        return time;
    }

    public static String getYear(Date date)
    {
        return FormatDate(date, "yyyy");
    }

    public static void main(String args[])
    {
        CodeDateUtils d = new CodeDateUtils();
        String strDate = "2007-02-11";
        Date aa = stringToDateShort(strDate);
        CodeDateUtils ddd = new CodeDateUtils();
    }

    public static Date stringToDate(String dateString)
    {
        if(dateString == null || dateString.trim().length() == 0)
            return null;
        String datestr = dateString.trim();
        String sf = "yyyy-MM-dd HH:mm:ss";
        Date dt = stringToDate(datestr, sf);
        if(dt == null)
            dt = stringToDate(datestr, "yyyy-MM-dd");
        if(dt == null)
            dt = stringToDate(datestr, "yyyyMMdd");
        return dt;
    }

    public static Date stringToDate(String dateString, String sf)
    {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(sf);
        Date dt = sdf.parse(dateString, pos);
        return dt;
    }

    public static Date stringToDateShort(String dateString)
    {
        String sf = "yyyy-MM-dd";
        Date dt = stringToDate(dateString, sf);
        return dt;
    }

    public CodeDateUtils()
    {
    }

    public String getBeginDate(String granularity, String statisticDate)
    {
        String beginDate = "";
        Date date = stringToDateShort(statisticDate);
        Date beginDateTemp = null;
        if(granularity.equals("1"))
            beginDateTemp = date;
        if(granularity.equals("2"))
            beginDateTemp = getWeekBegin(date);
        if(granularity.equals("3"))
            beginDateTemp = getPeriodBegin(date);
        else
        if(granularity.equals("4"))
            beginDateTemp = getMonthBegin(date);
        else
        if(granularity.equals("5"))
            beginDateTemp = getSeasonBegin(date);
        else
        if(granularity.equals("6"))
            beginDateTemp = getHalfYearBegin(date);
        else
        if(granularity.equals("7"))
            beginDateTemp = getYearBegin(date);
        beginDate = dateToStringShort(beginDateTemp);
        return beginDate;
    }

    public String getDateChangeALL(String currentTime, String type, int iQuantity)
    {
        Date curr = null;
        String newtype = "";
        if(currentTime.length() == 10)
            curr = stringToDateShort(currentTime);
        if(currentTime.length() > 10)
            curr = stringToDate(currentTime);
        if(type.equals("1"))
        {
            iQuantity = iQuantity;
            newtype = "d";
        } else
        if(type.equals("2"))
        {
            iQuantity *= 7;
            newtype = "d";
        } else
        if(type.equals("3"))
        {
            iQuantity *= 10;
            newtype = "d";
        } else
        if(type.equals("4"))
        {
            iQuantity = iQuantity;
            newtype = "m";
        } else
        if(type.equals("5"))
        {
            iQuantity *= 3;
            newtype = "m";
        } else
        if(type.equals("6"))
        {
            iQuantity *= 6;
            newtype = "m";
        } else
        if(type.equals("7"))
        {
            newtype = "y";
        } else
        {
            iQuantity = iQuantity;
            newtype = "d";
        }
        Date change = getDateChangeTime(curr, newtype, iQuantity);
        return dateToStringShort(change);
    }

    public Date getDateChangeTime(Date currentTime, String type, int iQuantity)
    {
        int year = Integer.parseInt(FormatDate(currentTime, "yyyy"));
        int month = Integer.parseInt(FormatDate(currentTime, "MM"));
        month--;
        int day = Integer.parseInt(FormatDate(currentTime, "dd"));
        int hour = Integer.parseInt(FormatDate(currentTime, "HH"));
        int mi = Integer.parseInt(FormatDate(currentTime, "mm"));
        int ss = Integer.parseInt(FormatDate(currentTime, "ss"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, mi, ss);
        if(type.equalsIgnoreCase("y"))
            gc.add(1, iQuantity);
        else
        if(type.equalsIgnoreCase("m"))
            gc.add(2, iQuantity);
        else
        if(type.equalsIgnoreCase("d"))
            gc.add(5, iQuantity);
        else
        if(type.equalsIgnoreCase("h"))
            gc.add(10, iQuantity);
        else
        if(type.equalsIgnoreCase("mi"))
            gc.add(12, iQuantity);
        else
        if(type.equalsIgnoreCase("s"))
            gc.add(13, iQuantity);
        return gc.getTime();
    }

    public String getDateChangeTime(String currentTime, String type, int iQuantity)
    {
        Date curr = stringToDate(currentTime);
        curr = getDateChangeTime(curr, type, iQuantity);
        return dateToString(curr);
    }

    public String getEndDate(String granularity, String statisticDate)
    {
        String beginDate = "";
        Date date = stringToDateShort(statisticDate);
        Date beginDateTemp = null;
        if(granularity.equals("1"))
            beginDateTemp = date;
        if(granularity.equals("2"))
            beginDateTemp = getWeekEnd(date);
        if(granularity.equals("3"))
            beginDateTemp = getPeriodEnd(date);
        else
        if(granularity.equals("4"))
            beginDateTemp = getMonthEnd(date);
        else
        if(granularity.equals("5"))
            beginDateTemp = getSeasonEnd(date);
        else
        if(granularity.equals("6"))
            beginDateTemp = getHalfYearEnd(date);
        else
        if(granularity.equals("7"))
            beginDateTemp = getYearEnd(date);
        beginDate = dateToStringShort(beginDateTemp);
        return beginDate;
    }

    public Date getHalfYearBegin(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-").toString();
        if(month <= 6)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("01-01").toString();
        else
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("07-01").toString();
        return stringToDateShort(newDateStr);
    }

    public Date getHalfYearEnd(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-").toString();
        if(month <= 6)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("06-30").toString();
        else
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("12-31").toString();
        return stringToDateShort(newDateStr);
    }

    public Date getMonthBegin(Date date)
    {
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy-MM")))).append("-01").toString();
        return stringToDateShort(newDateStr);
    }

    public Date getMonthEnd(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        int day = Integer.parseInt(FormatDate(date, "dd"));
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
        int monthLength = calendar.getActualMaximum(5);
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-").append(FormatDate(date, "MM")).append("-").toString();
        if(monthLength < 10)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("0").append(monthLength).toString();
        else
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append(monthLength).toString();
        return stringToDateShort(newDateStr);
    }

    public Date getPeriodBegin(Date date)
    {
        int days = Integer.parseInt(FormatDate(date, "dd"));
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy-MM")))).append("-").toString();
        if(days <= 10)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("01").toString();
        else
        if(days <= 20)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("11").toString();
        else
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("21").toString();
        return stringToDateShort(newDateStr);
    }

    public Date getPeriodEnd(Date date)
    {
        int days = Integer.parseInt(FormatDate(date, "dd"));
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy-MM")))).append("-").toString();
        if(days <= 10)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("10").toString();
        else
        if(days <= 20)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("20").toString();
        else
            newDateStr = FormatDate(getMonthEnd(date), "yyyy-MM-dd");
        return stringToDateShort(newDateStr);
    }

    public Date getSeasonBegin(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-").toString();
        if(month >= 1 && month <= 3)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("01-01").toString();
        else
        if(month >= 4 && month <= 6)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("04-01").toString();
        else
        if(month >= 7 && month <= 9)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("07-01").toString();
        else
        if(month >= 10 && month <= 12)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("10-01").toString();
        return stringToDateShort(newDateStr);
    }

    public Date getSeasonEnd(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-").toString();
        if(month >= 1 && month <= 3)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("03-31").toString();
        else
        if(month >= 4 && month <= 6)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("06-30").toString();
        else
        if(month >= 7 && month <= 9)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("09-30").toString();
        else
        if(month >= 10 && month <= 12)
            newDateStr = (new StringBuilder(String.valueOf(newDateStr))).append("12-31").toString();
        return stringToDateShort(newDateStr);
    }

    public String getTimedes(String granularity, String statisticDate)
    {
        String timedes = "";
        Date date = stringToDateShort(statisticDate);
        String year = "";
        String month = "01";
        String day = "01";
        year = getYear(date);
        month = getMonth(date);
        day = getDay(date);
        if(granularity.equals("1"))
            timedes = statisticDate;
        else
        if(granularity.equals("4"))
            timedes = (new StringBuilder(String.valueOf(year))).append("\u5E74").append(month).append("\u6708").toString();
        else
        if(granularity.equals("8"))
        {
            String quarter = (new StringBuilder(String.valueOf(month))).append("-").append(day).toString();
            if(quarter.equals("03-31"))
                timedes = (new StringBuilder(String.valueOf(year))).append("\u5E74 \u7B2C1\u5B63\u5EA6").toString();
            else
            if(quarter.equals("06-30"))
                timedes = (new StringBuilder(String.valueOf(year))).append("\u5E74 \u7B2C2\u5B63\u5EA6").toString();
            else
            if(quarter.equals("09-30"))
                timedes = (new StringBuilder(String.valueOf(year))).append("\u5E74 \u7B2C3\u5B63\u5EA6").toString();
            else
            if(quarter.equals("12-31"))
                timedes = (new StringBuilder(String.valueOf(year))).append("\u5E74 \u7B2C4\u5B63\u5EA6").toString();
        } else
        if(granularity.equals("32"))
            timedes = (new StringBuilder(String.valueOf(year))).append("\u5E74").toString();
        return timedes;
    }

    public Date getWeekBegin(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        month--;
        int day = Integer.parseInt(FormatDate(date, "dd"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        int week = 6;
        if(week == 0)
            week = 7;
        gc.add(5, (0 - week) + 1);
        return gc.getTime();
    }

    public Date getWeekEnd(Date date)
    {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        month--;
        int day = Integer.parseInt(FormatDate(date, "dd"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        int week = 6;
        if(week == 0)
            week = 7;
        gc.add(5, 7 - week);
        return gc.getTime();
    }

    public Date getYearBegin(Date date)
    {
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-01-01").toString();
        return stringToDateShort(newDateStr);
    }

    public Date getYearEnd(Date date)
    {
        String newDateStr = (new StringBuilder(String.valueOf(FormatDate(date, "yyyy")))).append("-12-31").toString();
        return stringToDateShort(newDateStr);
    }

    public boolean IsXperiodEnd(Date date)
    {
        boolean flag = false;
        String day = getDay(date);
        String month = getMonth(date);
        if(day.equalsIgnoreCase("10"))
            flag = true;
        else
        if(day.equalsIgnoreCase("20"))
            flag = true;
        return flag;
    }

    public static String addDay(String s, int n)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(5, n);
            return sdf.format(cd.getTime());
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static String delDay(String s, int n)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(5, -n);
            return sdf.format(cd.getTime());
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
