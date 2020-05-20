package com.jiangrongxin.train.file;

import java.io.File;

/**
 * @author Helay
 * @date 2020/5/19 19:21
 */
public class FileDemoTest {
    public static void main(String[] args) {
        String path = "C:\\tmp";
        File file = new File(path);
        FileDemo.traversalFie(file);
    }
}
