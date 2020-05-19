package com.jiangrongxin.train.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 向Map中添加元素
 *
 * @author Helay
 * @date 2020/5/14 17:02
 */
public class HashMapDemo {
    public static Map<String, String> add() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        return map;
    }
}
