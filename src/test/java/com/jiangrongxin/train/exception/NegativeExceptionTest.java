package com.jiangrongxin.train.exception;

import org.junit.Test;

/**
 * 测试自定义异常类
 * 《阿里巴巴Java开发手册》中强制规定测试类以测试的类的名称开始，以Test结束
 *
 * @author Helay
 * @date 2020/5/15 9:40
 */
public class NegativeExceptionTest {
    @Test
    public void negativeException() {
        NegativeExceptionDemo d = new NegativeExceptionDemo();
        try {
            // 调用div()方法，设置除数为负数
            int result = d.div(4, -1);
            System.out.println("result=" + result);
        } catch (NegativeException e) {
            System.out.println(e.toString() + "，异常的除数为：" + e.getValue());
        }
    }
}
