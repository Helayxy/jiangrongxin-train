package com.jiangrongxin.train.collection.list.linkedlist;

import java.util.LinkedList;

/**
 * 使用LinkedList模拟队列数据结构
 * 队列的特点：先进先出
 *
 * @author Helay
 * @date 2020/5/14 12:54
 */
public class Queue {

    private LinkedList linkedList;

    /**
     * 无参构造，初始化stack对象就相当于创建了一个LinkedList对象
     */
    public Queue() {
        linkedList = new LinkedList();
    }

    /**
     * 自定义一个添加元素的方法，实质就是调用LinkedList的addFirst()方法
     * addFirst()方法：在此列表开头插入指定元素
     *
     * @param obj
     */
    public void myAdd(Object obj) {
        linkedList.addFirst(obj);
    }

    /**
     * 定义一个删除元素的方法，实质就是调用LinkedList的removeLast()方法
     * removeLast()方法：从此列表中删除并返回最后一个元素
     *
     * @return
     */
    public Object myGet() {
        return linkedList.removeLast();
    }

    /**
     * 定义一个判断集合是否为空的方法，实质就是调用LinkedList的isNull()方法
     * isNull()方法：如果此列表不包含元素，则返回true
     *
     * @return
     */
    public boolean isNull() {
        return linkedList.isEmpty();
    }
}
