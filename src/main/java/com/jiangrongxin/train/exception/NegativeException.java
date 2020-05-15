package com.jiangrongxin.train.exception;

/**
 * @author Helay
 * @date 2020/5/15 9:31
 */
public class NegativeException extends Exception {

    //value用于保存发生异常的数
    private int value;

    /**
     * 无参构造
     */
    public NegativeException() {
        super();
    }

    /**
     * 有参构造
     *
     * @param message
     * @param value
     */
    public NegativeException(String message, int value) {
        super(message);
        this.value = value;
    }

    /**
     * 获取发生异常的值
     *
     * @return
     */
    public int getValue() {
        return value;
    }
}
