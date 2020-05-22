package com.jiangrongxin.train.socket;

import org.junit.Test;

import java.io.IOException;

/**
 * 获取网页内容
 *
 * @author Helay
 * @date 2020/5/22 7:43
 */
public class ServerSocketTest {
    @Test
    public void getPageContent() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.getWebPage();
    }
}
