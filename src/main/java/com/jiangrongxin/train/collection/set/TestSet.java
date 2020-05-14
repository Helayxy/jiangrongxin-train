package com.jiangrongxin.train.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试Set存储元素的唯一性
 *
 * @author Helay
 * @date 2020/5/14 10:23
 */
public class TestSet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("hello world");
        set.add("江融信科技");
        set.add("江融信科技");
        System.out.println("集合中元素个数：" + set.size());
        System.out.println("集合中元素为：" + set.toString());
    }
}
