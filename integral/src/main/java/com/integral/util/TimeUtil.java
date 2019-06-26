package com.integral.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.util
 * @date:2019/5/25 时间工具类
 **/
public class TimeUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh-mm-ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMMDD = "yyyyMMddHHmm";
    //private Map<>
    private static SimpleDateFormat simpleDateFormat = null;

    /**
     * 返回String类型时间
     *
     * @param format
     * @param date
     * @return
     */
    public static String foramt(String format, Date date) {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 返回Date类型时间
     *
     * @param format
     * @param date
     * @return
     */
    public static Date format(String format, Date date) {
        simpleDateFormat = new SimpleDateFormat(format);
        String time = simpleDateFormat.format(date);
        Date formartDate = null;
        try {
            formartDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return formartDate;
    }

    /**
     * 将字符传时间转成Date
     *
     * @param format
     * @param date
     * @return
     */
    public static Date format(String format, String date) {
        simpleDateFormat = new SimpleDateFormat(format);
        Date formatDate = null;
        //try {
        //formatDate = simpleDateFormat.parse(date);
        formatDate = simpleDateFormat.parse(date, new ParsePosition(4));
       /* }catch (ParseException e){
            e.printStackTrace();
            return null;
        }*/
        return formatDate;
    }
}
