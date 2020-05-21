package com.jiangrongxin.train.file;

/**
 * @author Helay
 * @date 2020/5/21 21:50
 */
public class BigFileReaderTest {
    public static void main(String[] args) {
        BigFileReader.Builder builder = new BigFileReader.Builder("C:\\logs\\xc.2020-05-21.log", new IHandle() {

            @Override
            public void handle(String line) {
                //如需打印文件内容，可将此打印语句注释放开
                //System.out.println(line);
            }
        });
        builder.withThreadSize(10)
                .withCharset("gbk")
                .withBufferSize(1024 * 1024);
        BigFileReader bigFileReader = builder.build();
        bigFileReader.start();
    }
}
