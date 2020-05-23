package com.jiangrongxin.train.regular;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该示例用于练习正则表达式的使用
 *
 * @author Helay
 * @date 2020/5/19 18:09
 */
public class Address {
    /**
     * 使用正则表达式提取一个地址中需要的信息
     *
     * @param address
     * @return
     */
    public static List<Map<String, String>> extractAddress(String address) {
        /*
         * ^：表示一个字符串的开始；
         * ()：标记一个子表达式；
         * ?：匹配前面的子表达式0次或1次；
         * |：指明两项之间的一个选择；
         * .：匹配除换行符\n之外的任何单个字符；
         */
        //定义一个表达式规则（正则）
        String regex = "(?<country>[^国]+国)((?<province>[^省]+省)(?<city>[^市]+市)(?<district>[^区]+区)(?<street>[^街]+街)(?<door>.*))";

        //将给定的正则表达式编译为一个模式
        Pattern pattern = Pattern.compile(regex);

        //匹配指定的输入内容,并返回一个匹配器
        Matcher matcher = pattern.matcher(address);

        //定义需要的数据并初始化，局部变量需要初始化
        String country = null, province = null, city = null, district = null, street = null, door = null;

        //创建一个ArrayList用来存结果集
        List<Map<String, String>> result = new ArrayList<>();

        //使用Map记录提取出来的每一行的数据
        Map<String, String> row = null;

        //判断是否存在符合匹配模式的下一个子序列
        while (matcher.find()) {
            //这里使用LinkedHashMap是要保证元素的有序性
            row = new LinkedHashMap<String, String>();
            //返回与上一个匹配匹配的输入子序列
            country = matcher.group("country");
            //trim()方法：用于删除字符串的头尾空白符
            row.put("country", country == null ? "" : country.trim());

            province = matcher.group("province");
            row.put("province", province == null ? "" : province.trim());

            city = matcher.group("city");
            row.put("city", city == null ? "" : city.trim());

            district = matcher.group("district");
            row.put("district", district == null ? "" : district.trim());

            street = matcher.group("street");
            row.put("street", street == null ? "" : street.trim());

            door = matcher.group("door");
            row.put("door", door == null ? "" : door.trim());

            //将提取到的数据放入ArrayList中
            result.add(row);
        }
        return result;
    }
}
