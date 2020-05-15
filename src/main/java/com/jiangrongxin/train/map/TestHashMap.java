package com.jiangrongxin.train.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 该示例用于演示遍历HashMap的几种方式
 *
 * @author Helay
 * @date 2020/5/14 17:02
 */
public class TestHashMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        //第一种：通过key，遍历value
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }

        //第二种：通过迭代器进行遍历
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //第三种：使用foreach遍历entrySet，推荐使用，尤其对于map元素较多时
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //第四种：只遍历value值
        for (String value : map.values()) {
            System.out.println(value);
        }

    }
}
