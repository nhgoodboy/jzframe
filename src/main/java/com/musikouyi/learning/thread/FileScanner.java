package com.musikouyi.learning.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2019/3/5 22:13
 **/
public class FileScanner {

    private static void listFile(File f) throws InterruptedException {
        if (f == null) {
            throw new IllegalArgumentException();
        }
        if (f.isFile()) {
            System.out.println(f);
            return;
        }
        File[] allFiles = f.listFiles();
        if (Thread.interrupted()) {
            throw new InterruptedException("文件扫描任务被中断");
        }
        assert allFiles != null;
        for (File file : allFiles) {
            // 还可以将中断检测放到这里
            listFile(file);
        }
    }

    private static String readFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        final Thread fileIteratorThread = new Thread(() -> {
            try {
                listFile(new File("F:\\OtherFiles\\杂件"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        new Thread(() -> {
            while (true) {
                if ("quit".equalsIgnoreCase(readFromConsole())) {
                    if (fileIteratorThread.isAlive()) {
                        fileIteratorThread.interrupt();
                        return;
                    }
                } else {
                    System.out.println("输入 quit 退出文件扫描");
                }
            }
        }).start();
        fileIteratorThread.start();
    }
}

