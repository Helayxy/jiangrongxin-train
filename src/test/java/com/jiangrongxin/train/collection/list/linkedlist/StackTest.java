package com.jiangrongxin.train.collection.list.linkedlist;

import com.jiangrongxin.train.collection.list.linkedlist.Stack;

/**
 * 测试LinkedList实现栈效果
 *
 * @author Helay
 * @date 2020/5/14 12:37
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.myAdd("江融信01");
        stack.myAdd("江融信02");
        stack.myAdd("江融信03");
        stack.myAdd("江融信04");
        //不为空时，开始输出
        while (!stack.isNull()) {
            System.out.println(stack.myGet());
        }
    }
}
