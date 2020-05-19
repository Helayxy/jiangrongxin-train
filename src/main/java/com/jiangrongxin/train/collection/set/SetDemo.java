package com.jiangrongxin.train.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * 向Set中添加元素
 *
 * @author Helay
 * @date 2020/5/14 10:23
 */
public class SetDemo {
    public static Set<String> add() {
        Set<String> set = new HashSet<>();
        set.add("hello world");
        set.add("江融信科技");
        set.add("江融信科技");
        return set;
    }
}
