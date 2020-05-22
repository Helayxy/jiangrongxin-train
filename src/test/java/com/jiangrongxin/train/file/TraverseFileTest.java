package com.jiangrongxin.train.file;

import org.junit.Test;

import java.io.File;

/**
 * 遍历目录下的文件，包括子目录
 *
 * @author Helay
 * @date 2020/5/19 19:21
 */
public class TraverseFileTest {
    @Test
    public void traverseFile() {
        String path = "C:\\tmp";
        File file = new File(path);
        TraverseFile.traverseTheFile(file);
    }
}
