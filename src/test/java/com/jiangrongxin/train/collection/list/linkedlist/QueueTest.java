package com.jiangrongxin.train.collection.list.linkedlist;

import org.junit.Test;

/**
 * 测试LinkedList实现队列效果
 *
 * @author Helay
 * @date 2020/5/14 12:58
 */
public class QueueTest {
    @Test
    public void getQueue() {
        Queue queue = new Queue();
        queue.myAdd("江融信科技01");
        queue.myAdd("江融信科技02");
        queue.myAdd("江融信科技03");
        queue.myAdd("江融信科技04");
        // 不为空时，开始输出
        while (!queue.isNull()) {
            System.out.println(queue.myGet());
        }
    }
}
