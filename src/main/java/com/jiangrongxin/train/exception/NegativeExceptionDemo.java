package com.jiangrongxin.train.exception;

/**
 * @author Helay
 * @date 2020/5/15 9:35
 */
public class NegativeExceptionDemo {

    public int div(int a, int b) throws NegativeException {
        if (b < 0) {
            throw new NegativeException("出现了除数为负数的情况", b);
        }
        return a / b;
    }
}
