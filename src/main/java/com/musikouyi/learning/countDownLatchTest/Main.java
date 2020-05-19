package com.musikouyi.learning.countDownLatchTest;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2019/3/8 20:52
 **/
public class Main {
    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}