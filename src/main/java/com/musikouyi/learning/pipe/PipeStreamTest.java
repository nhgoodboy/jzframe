package com.musikouyi.learning.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道输入流和管道输出流的交互程序
 */
public class PipeStreamTest {

    public static void main(String[] args) {
        Sender t1 = new Sender();

        Receiver t2 = new Receiver();

        PipedOutputStream out = t1.getOutputStream();

        PipedInputStream in = t2.getInputStream();

        try {
            //管道连接。下面2句话的本质是一样。
            //out.connect(in);
            in.connect(out);

            /**
             * Thread类的START方法：
             * 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
             * 结果是两个线程并发地运行；当前线程（从调用返回给 start 方法）和另一个线程（执行其 run 方法）。
             * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
             */
            t1.start();
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
