package com.musikouyi.learning.countDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2019/3/8 20:52
 **/
public class CacheHealthChecker extends BaseHealthChecker
{
    public CacheHealthChecker (CountDownLatch latch)  {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
