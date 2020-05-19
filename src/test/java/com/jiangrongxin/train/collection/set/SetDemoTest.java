package com.jiangrongxin.train.collection.set;

import java.util.Set;

/**
 * 测试Set接口的唯一性
 *
 * @author Helay
 * @date 2020/5/19 18:01
 */
public class SetDemoTest {
    public static void main(String[] args) {
        Set<String> set = SetDemo.add();
        System.out.println("集合中元素个数：" + set.size());
        System.out.println("集合中元素为：" + set.toString());
    }
}
