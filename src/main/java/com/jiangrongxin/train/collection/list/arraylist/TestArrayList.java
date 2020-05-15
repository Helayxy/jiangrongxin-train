package com.jiangrongxin.train.collection.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 该示例用于演示遍历ArrayList的几种方式
 *
 * @author Helay
 * @date 2020/5/14 16:47
 */
public class TestArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("江融信");

        //第一种：普通for循环遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //第二种：使用foreach遍历
        for (String str : list) {
            System.out.println(str);
        }

        //第三种：使用迭代器遍历
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
