package me.jcala.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * Created by Administrator on 2016/9/17.
 */
public class TimeTools {
    private static final Calendar CALENDAR =  Calendar.getInstance();
    /**
     * 获取当前月往前数前两个月的月份
     */
    public static String last2Month(){
        Date date = new Date();//当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对象
        CALENDAR.setTime(date);//设置当前日期
        CALENDAR.add(Calendar.MONTH, -2);
        return sdf.format(CALENDAR.getTime());
    }
    /**
     *  返回年份
     *
     *  @param  date
     *                        日期
     *  @return  返回年份
     */
    public  static  int  getYear(Date  date)  {
        CALENDAR.setTime(date);
        return  CALENDAR.get(Calendar.YEAR);
    }

    public static String getEdate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("MMM dd", Locale.UK);
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    public static String getYearMonthOfNow(){
        CALENDAR.setTime(new Date());
        int year=CALENDAR.get(Calendar.YEAR);
        int month=CALENDAR.get(Calendar.MONTH);
        return year+""+month;
    }

}
