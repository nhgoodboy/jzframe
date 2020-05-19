package com.musikouyi.learning;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 应该在循环中检查等待条件
 * (处于等待状态的线程可能会收到错误警报和伪唤醒，如果不在循环中检查等待条件，程序就会在没有满足结束条件的情况下退出。)
 */
public class EarlyNotify {

    private final List<String> list;

    private EarlyNotify() {
        list = Collections.synchronizedList(new LinkedList<>());
    }

    private String removeItem() throws InterruptedException {
        print("in removeItem() - entering");

        synchronized (list) {
            while (list.isEmpty()) {  //这里用if语句会发生危险
                print("in removeItem() - about to wait()");
                list.wait();
                print("in removeItem() - done with wait()");
            }

            //删除元素
            String item = list.remove(0);

            print("in removeItem() - leaving");
            return item;
        }
    }

    private void addItem(String item) {
        print("in addItem() - entering");
        synchronized (list) {
            //添加元素
            list.add(item);
            print("in addItem() - just added: '" + item + "'");

            //添加后，通知所有线程
            list.notifyAll();
            print("in addItem() - just notified");
        }
        print("in addItem() - leaving");
    }

    private static void print(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + msg);
    }

    public static void main(String[] args) {
        final EarlyNotify en = new EarlyNotify();

        Runnable runA = () -> {
            try {
                String item = en.removeItem();
                print("in run() - returned: '" +
                        item + "'");
            } catch (InterruptedException ix) {
                print("interrupted!");
            } catch (Exception x) {
                print("threw an Exception!!!\n" + x);
            }
        };

        Runnable runB = () -> en.addItem("Hello!");

        try {
            //启动第一个删除元素的线程
            Thread threadA1 = new Thread(runA, "threadA1");
            threadA1.start();

            Thread.sleep(500);

            //启动第二个删除元素的线程
            Thread threadA2 = new Thread(runA, "threadA2");
            threadA2.start();

            Thread.sleep(500);
            //启动增加元素的线程
            Thread threadB = new Thread(runB, "threadB");
            threadB.start();

            Thread.sleep(10000); // wait 10 seconds

            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException ignored) {
        }
    }
}
