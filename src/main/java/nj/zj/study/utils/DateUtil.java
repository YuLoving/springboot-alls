package nj.zj.study.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 自定义日期处理类
 * @author mustang.yu
 * util.Date、util.Calendar
 */
public class DateUtil {

    //提供常用的DateFormat格式
    public static final String hour24YMDHMS = "yyyy-MM-dd HH:mm:ss";//例：2019-05-09 10:09:46 24小时制
    public static final String hour12YMDHMS = "yyyy-MM-dd hh:mm:ss";//例：2019-05-09 03:12:33 12小时制
    public static final String ch24YMDHMS = "yyyy年MM月dd日 HH时mm分ss秒";//例：2019年05月09日 15时17分01秒  24小时制
    public static final String ch12YMDHMS = "yyyy年MM月dd日 hh时mm分ss秒";//例：2019年05月09日 03时17分49秒  12小时制

    /**
     * 计算utilDate相差的天数
     * util.Date:getTime()----毫秒级时间戳
     * @author mustang.yu
     */
    public static int getIntervalDays(Date startDate, Date endDate){
        return (int) ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 计算当前日期多少天之后的日期(一)
     * @author:mustang.yu
     * return: util.Date
     */
    public static Date getAppointedAfterCurUtilDate (int countDay){
        Date resoleDate = new Date();
        //获取当前时间时间戳
        long curTime = resoleDate.getTime();
        long appointTime = curTime + 24*60*60*1000*countDay;
        resoleDate.setTime(appointTime);
        return resoleDate;
    }

    /**
     * 计算当前日期多少天之后的日期(二)
     * @author:mustang.yu
     * return: util.Date
     */
    public static Date getAppointedAfterCurCalenDar(int countDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,countDay);
        return calendar.getTime();
    }

    /**
     * 格式化日期字符串并输出
     * @author:mustang.yu
     */
    public static String exportFormatUtilDate (Date selfDate,String selfFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(selfFormat);
        return sdf.format(selfDate);
    }

    /**
     * 比较时间大小
     * @author:mustang.yu
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2){
        if (date1.getTime() > date2.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 获取选择时间前几天时间
     * @author:mustang.yu
     *  @param days
     */
    public static Date getDaysBefore(int days, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 取得两个日期的间隔天数
     * @author:mustang.yu
     * @param date1
     * @param date2
     * @return
     */
    public static int getDiffDays(Date date1, Date date2) {
        long from = date1.getTime();//毫秒级
        long to = date2.getTime();
        //一天等于多少毫秒：24*3600*1000
        int day = (int)((to-from)/(24*60*60*1000));
        return day;
    }

}
