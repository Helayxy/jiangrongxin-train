package com.jiangrongxin.train.collection.list.arraylist;

import java.util.Iterator;
import java.util.List;

/**
 * 使用不同的方式遍历ArrayList
 *
 * @author Helay
 * @date 2020/5/19 17:27
 */
public class ArrayListDemoTest {
    public static void main(String[] args) {
        List<String> list = ArrayListDemo.add();

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
