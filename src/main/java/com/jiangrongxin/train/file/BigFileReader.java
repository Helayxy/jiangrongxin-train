package com.jiangrongxin.train.file;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 可以读取10万行数据
 * 解题思路：大文件数据的读取，要保证高效的话，就需要用到NIO和多线程的技术
 * 1、对文件分区：
 * 1）为了充分利用多线程读取，就需要把文件划分成多个区域，供每个线程读取；
 * 2）需要有一个算法来计算出每个线程读取的开始位置和结束位置；
 * 3）首先根据配置的线程数和文件的总长度计，算出每个线程平均分配的读取长度；
 * 4）需要注意的是，由于文件是纯文本文件，必须按行来处理，如果分割点在某一行中间，那么这一行数据就会被分成两部分，分别由两个线程同时处理；
 * 5）所以各个区域的结束点上的字符必须是换行符；
 * 6）第一个区域的开始位置是0，结束位置首先设为（文件长度/线程数），如果结束点位置不是换行符，就只能加1，直到是换行符位置；
 * 7）第一个区域的结束位置有了，我们就能求出第二个区域的开始位置了，根据上边算法求出第二个区域的结束位置，依次类推第三个、第四个；
 * 8）上述这个算法需要用递归来实现
 *
 * @author Helay
 * @date 2020/5/21 20:52
 */
public class BigFileReader {
    private int threadSize;//线程大小
    private String charset;//字符集
    private int bufferSize;//缓冲大小
    private IHandle handle;//自定义处理接口
    private ExecutorService executorService;//异步执行
    private long fileLength;//文件大小
    private RandomAccessFile randomAccessFile;//读取文件，输出数据
    private Set<StartEndPair> startEndPairs;//使用Set集合存储每个分片的片段
    private CyclicBarrier cyclicBarrier;//回环栅栏
    private AtomicLong counter = new AtomicLong(0);//原子变量初始值大小

    /**
     * 有参构造
     *
     * @param file
     * @param handle
     * @param charset
     * @param bufferSize
     * @param threadSize
     */
    private BigFileReader(File file, IHandle handle, String charset, int bufferSize, int threadSize) {
        this.fileLength = file.length();
        this.handle = handle;
        this.charset = charset;
        this.bufferSize = bufferSize;
        this.threadSize = threadSize;
        try {
            this.randomAccessFile = new RandomAccessFile(file, "r");//初始化RandomAccessFile类的实例对象
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.executorService = Executors.newFixedThreadPool(threadSize);//初始化线程池
        startEndPairs = new HashSet<>();
    }

    /**
     * 开始读取数据
     */
    public void start() {
        //每个线程平均读取文件的大小
        long everySize = this.fileLength / this.threadSize;
        try {
            //递归分片
            calculateStartEnd(0, everySize);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //使用系统当前时间作为开始时间
        final long startTime = System.currentTimeMillis();
        cyclicBarrier = new CyclicBarrier(startEndPairs.size(), new Runnable() {

            @Override
            public void run() {
                System.out.println("总用时：" + (System.currentTimeMillis() - startTime) + "毫秒");
                System.out.println("文件总行数：" + counter.get() + "行");
            }
        });
        for (StartEndPair pair : startEndPairs) {
            System.out.println("分配分片：" + pair);
            this.executorService.execute(new SliceReaderTask(pair));
        }
    }

    /**
     * 递归分片
     *
     * @param start
     * @param size
     * @throws IOException
     */
    private void calculateStartEnd(long start, long size) throws IOException {
        //如果开始的位置大于文件-1的大小，则直接返回
        if (start > fileLength - 1) {
            return;
        }
        StartEndPair pair = new StartEndPair();
        pair.start = start;
        long endPosition = start + size - 1;
        //如果是最后一个分片
        if (endPosition >= fileLength - 1) {
            pair.end = fileLength - 1;
            startEndPairs.add(pair);
            return;
        }
        //还有没有其他分片的数据，将指针移动到上一个分片的末尾
        randomAccessFile.seek(endPosition);
        //由于分割的地方可能在一行数据，所以将结束位置一直移动到这一行
        byte tmp = (byte) randomAccessFile.read();
        while (tmp != '\n' && tmp != '\r') {
            endPosition++;
            if (endPosition >= fileLength - 1) {
                endPosition = fileLength - 1;
                break;
            }
            randomAccessFile.seek(endPosition);
            tmp = (byte) randomAccessFile.read();
        }
        pair.end = endPosition;
        startEndPairs.add(pair);
        //递归分片
        calculateStartEnd(endPosition + 1, size);
    }

    /**
     * 将每行数据转成指定格式的字符串
     *
     * @param bytes
     * @throws UnsupportedEncodingException
     */
    private void handle(byte[] bytes) throws UnsupportedEncodingException {
        String line = null;
        if (this.charset == null) {
            line = new String(bytes);
        } else {
            line = new String(bytes, charset);
        }
        if (line != null && !"".equals(line)) {
            this.handle.handle(line);
            counter.incrementAndGet();
        }
    }

    /**
     * 每个分片的片段
     * 需要重写hashCode、equals方法判断是否为同一片段
     */
    private static class StartEndPair {
        //片段的起始位置
        public long start;
        //片段的结束位置
        public long end;

        @Override
        public String toString() {
            return "star=" + start + ";end=" + end;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (end ^ (end >>> 32));
            result = prime * result + (int) (start ^ (start >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            StartEndPair other = (StartEndPair) obj;
            if (end != other.end)
                return false;
            if (start != other.start)
                return false;
            return true;
        }

    }

    /**
     * 读取一个分片的数据线程
     */
    private class SliceReaderTask implements Runnable {
        private long start;
        private long sliceSize;
        private byte[] readBuff;

        /**
         * @param pair
         */
        public SliceReaderTask(StartEndPair pair) {
            this.start = pair.start;
            this.sliceSize = pair.end - pair.start + 1;
            this.readBuff = new byte[bufferSize];
        }

        @Override
        public void run() {
            try {
                MappedByteBuffer mapBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, start, this.sliceSize);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                for (int offset = 0; offset < sliceSize; offset += bufferSize) {
                    int readLength;
                    if (offset + bufferSize <= sliceSize) {
                        readLength = bufferSize;
                    } else {
                        readLength = (int) (sliceSize - offset);
                    }
                    mapBuffer.get(readBuff, 0, readLength);
                    for (int i = 0; i < readLength; i++) {
                        byte tmp = readBuff[i];
                        //判断换行
                        if (tmp == '\n' || tmp == '\r') {
                            handle(bos.toByteArray());
                            bos.reset();
                        } else {
                            bos.write(tmp);
                        }
                    }
                }
                if (bos.size() > 0) {
                    handle(bos.toByteArray());
                }
                //测试性能
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 构建BigFileReader类
     */
    public static class Builder {
        private int threadSize = 1;
        private String charset = null;
        private int bufferSize = 1024 * 1024;
        private IHandle handle;
        private File file;

        public Builder(String file, IHandle handle) {
            this.file = new File(file);
            if (!this.file.exists())
                throw new IllegalArgumentException("文件不存在！");
            this.handle = handle;
        }

        public Builder withThreadSize(int size) {
            this.threadSize = size;
            return this;
        }

        public Builder withCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public Builder withBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public BigFileReader build() {
            return new BigFileReader(this.file, this.handle, this.charset, this.bufferSize, this.threadSize);
        }
    }
}
