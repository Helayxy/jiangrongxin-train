package com.jiangrongxin.train.file;

import java.io.File;

/**
 * @author Helay
 * @date 2020/5/19 19:21
 */
public class TraverseFileTest {
    public static void main(String[] args) {
        String path = "C:\\tmp";
        File file = new File(path);
        TraverseFile.traverseTheFile(file);
    }
}
