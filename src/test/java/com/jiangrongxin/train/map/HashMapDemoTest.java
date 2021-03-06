package com.jiangrongxin.train.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * 使用不同的方式遍历Map中的元素
 *
 * @author Helay
 * @date 2020/5/19 17:50
 */
public class HashMapDemoTest {
    @Test
    public void traverseHashMap() {
        Map<String, String> map = HashMapDemo.add();

        // 第一种：通过key，遍历value
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }

        // 第二种：通过迭代器进行遍历
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 第三种：使用foreach遍历entrySet，推荐使用，尤其对于map元素较多时
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 第四种：只遍历value值
        for (String value : map.values()) {
            System.out.println(value);
        }
    }
}
