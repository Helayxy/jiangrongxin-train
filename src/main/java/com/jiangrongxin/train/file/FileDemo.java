package com.jiangrongxin.train.file;

import java.io.File;

/**
 * 遍列当前目录下所有文件，包括子目录
 *
 * @author Helay
 * @date 2020/5/19 19:09
 */
public class FileDemo {
    /**
     * 递归遍历文件，可以遍历出隐藏文件夹
     */
    public static void traversalFie(File file) {
        //获取文件所在的路径名，以数组形式表示
        File[] files = file.listFiles();
        //遍历该路径名
        for (File f : files) {
            //如果是目录，则继续递归遍历
            if (f.isDirectory()) {
                traversalFie(f);
            }
            //如果是文件，直接输出
            if (f.isFile()) {
                System.out.println(f);
            }
        }

    }
}
