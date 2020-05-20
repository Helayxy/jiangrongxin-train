package com.jiangrongxin.train.util;

import java.util.Date;

import static com.jiangrongxin.train.util.DateUtils.*;

/**
 * 测试日期工具类
 *
 * @author Helay
 * @date 2020/5/19 20:04
 */
public class DateUtilsTest {
    public static void main(String[] args) {
        String strDate = "2020-05-20";
        Date date = parseDate(strDate);
        System.out.println("格式化日期前：" + date);
        System.out.println("格式化日期后：" + formatDate(date));
        System.out.println("年份：" + getYear(date) + "年");
        System.out.println("月份：" + getMonth(date) + "月");
        System.out.println("日期：" + getDay(date) + "日");
        System.out.println("季度：第" + getSeason(date) + "季度");
        System.out.println("星期数：星期" + getDayOfWeek(date));
        System.out.println("在当前年份中是第" + getDayOfYear(strDate) + "天");
        System.out.println("增加月份前：" + getMonth(date) + "月，" + "增加两个月份后：" + addMonth(date) + "月");
        System.out.println("减去月份前：" + getMonth(date) + "月，" + "减去两个月份后：" + reduceMonth(date) + "月");
        System.out.println("增加日期前：" + getDay(date) + "日，" + "增加6天日期后：" + addDay(date) + "日");
        addHour();
        reduceHour();
    }
}
