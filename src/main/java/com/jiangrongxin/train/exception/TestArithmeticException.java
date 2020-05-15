package com.jiangrongxin.train.exception;

import java.util.Scanner;

/**
 * 该示例用于演示算术运算异常
 *
 * @author Helay
 * @date 2020/5/14 18:51
 */
public class TestArithmeticException {
    public static void main(String[] args) {
        System.out.println("----欢迎使用命令行除法计算器----");
        CMDCalculate();
    }

    /**
     * 使用命令行的方式录入
     */
    public static void CMDCalculate() {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int result = divide(num1, num2);
        System.out.println(result);
        scanner.close();
    }

    /**
     * 定义一个除法运算
     *
     * @param num1
     * @param num2
     * @return
     */
    public static int divide(int num1, int num2) {
        return num1 / num2;
    }

}
