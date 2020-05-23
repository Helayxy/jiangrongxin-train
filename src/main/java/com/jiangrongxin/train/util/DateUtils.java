package com.jiangrongxin.train.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Helay
 * @date 2020/5/19 19:42
 */
public class DateUtils {
    /**
     * Date and Time Pattern              Result
     * "yyyy.MM.dd G 'at' HH:mm:ss z"     2001.07.04 AD at 12:08:56 PDT
     * "EEE, MMM d, ''yy"                 Wed, Jul 4, '01
     * "h:mm a"                           12:08 PM
     * "hh 'o''clock' a, zzzz"            12 o'clock PM, Pacific Daylight Time
     * "K:mm a, z"                        0:08 PM, PDT
     * "yyyyy.MMMMM.dd GGG hh:mm aaa"     02001.July.04 AD 12:08 PM
     * "EEE, d MMM yyyy HH:mm:ss Z"       Wed, 4 Jul 2001 12:08:56 -0700
     * "yyMMddHHmmssZ"                    010704120856-0700
     * "yyyy-MM-dd'T'HH:mm:ss.SSSZ"       2001-07-04T12:08:56.235-0700
     * "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"     2001-07-04T12:08:56.235-07:00
     * "YYYY-'W'ww-u"                     2001-W27-3
     */
    public static final String PATTERN_1 = "yyyy-MM-dd";
    public static final String PATTERN_2 = "yyyy年MM月dd日";

    /**
     * 将String类型的字符串解析为Date类型的日期
     *
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, null);
    }

    /**
     * 解析指定格式的日期
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDate(String strDate, String pattern) {
        Date date = null;
        try {
            if (pattern == null) {
                pattern = PATTERN_1;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, null);
    }

    /**
     * 按照指定的格式格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = PATTERN_2;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        //获取日历的实例对象
        Calendar calendar = Calendar.getInstance();
        //给当前日历设置时间
        calendar.setTime(date);
        //获取日历的年份
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        //这里注意要将返回的月份加1才是中国的日期格式
        return month + 1;
    }

    /**
     * 获取日期
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //使用月份进行季度划分
        int month = c.get(Calendar.MONTH);
        //使用switch语句进行判断
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                System.out.println("日期有误！");
        }
        return season;
    }

    /**
     * 获取星期数
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_week = c.get(Calendar.DAY_OF_WEEK);
        return week_of_week - 1;
    }

    /**
     * 日期格式：2020-05-20
     *
     * @param date
     * @return
     */
    public static int getDayOfYear(String date) {
        //使用数组存放内个月的天数，这里2月份的天数按照平年计算
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //字符串的前四位为年份，取出前四位并转换为int类型
        int year = Integer.parseInt(date.substring(0, 4));
        //取出月份
        int month = Integer.parseInt(date.substring(5, 7));
        //取出日期
        int day = Integer.parseInt(date.substring(8, 10));
        //数组下标从0开始，所以月份要减1
        for (int i = 0; i < month - 1; i++) {
            day += days[i];
        }
        //平年和闰年的区别在于2月份的天数不一样，如果是闰年，月份大于等于3，则天数要加1
        return isLeapYear(year) && month >= 3 ? day + 1 : day;
    }

    /**
     * 判断当前年份是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        //普通闰年：能被4整除，但后两位不以00结尾
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        //世纪闰年：能被400整除,且后两位以00结尾
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 增加月份
     *
     * @param date
     * @return
     */
    public static int addMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //月份加2，两个参数，第一个为日期类型，第二个为偏移量，正数表示加，负数表示减
        calendar.add(Calendar.MONTH, 2);
        int month = calendar.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 减少月份
     *
     * @param date
     * @return
     */
    public static int reduceMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -2);
        int month = calendar.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 增加日期
     *
     * @param date
     * @return
     */
    public static int addDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 增加小时
     *
     * @return
     */
    public static void addHour() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int beforeHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.print("增加小时前：" + beforeHour + "时");
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        System.out.print("，");
        int afterHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("增加两个小时后：" + afterHour + "时");
    }

    /**
     * 减少小时
     */
    public static void reduceHour() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int beforeHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.print("减少小时前：" + beforeHour + "时");
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        System.out.print("，");
        int afterHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("减少两个小时后：" + afterHour + "时");
    }
}
