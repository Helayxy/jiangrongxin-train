package com.jiangrongxin.train.socket;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 使用网络编程Socket读取网页内容
 * 解题思路：以读取百度首页为例
 * 2）如果知道了IP地址和端口号，然后就新建一个Socket去读百度的首页，这时没有数据返回；
 * 3）原因是www.baidu.com是以http协议传输的，而现在要以Socket原始的套接字读写，远程服务器没有收到客户端的GET报文，故无法做出响应；
 * 4）正确的方式是在Socket输出流中写入HTTP的GET报文，输出流中的报文告诉服务器自己要GET的网页，这样服务器才会响应我们的请求。
 *
 * @author Helay
 * @date 2020/5/22 7:26
 */
public class ServerSocket {

    /**
     * 读取网页内容
     */
    public void getWebPage() throws IOException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        Socket socket = new Socket(inetAddress.getHostAddress(), 80);
        // 判断连接是否成功
        if (socket.isConnected()) {
            System.out.println("连接成功，远程地址是：" + socket.getRemoteSocketAddress());
        }
        // 在Socket的输出流中写入HTTP的GET报文，请求服务器响应
        // 使用缓冲提高效率，需要将字节流转换成字符流，将我们要提交的数据写到输出流中
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("GET / HTTP/1.1\r\n");
        writer.write("Host: www.baidu.com\r\n");
        writer.write("\r\n");
        // 涉及到字符流操作都要刷新，否则内容读写不成功
        writer.flush();
        // 开始读取服务器响应回来的数据
        BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
        // 定义缓冲区大小
        byte[] buffer = new byte[1024];
        int count = 0;
        while (true) {
            count = inputStream.read(buffer);
            if (count == -1) {
                break;
            }
            System.out.println(new String(buffer, 0, count, "utf-8"));
        }
        // 释放资源
        writer.close();
        inputStream.close();
        socket.close();
    }

}
