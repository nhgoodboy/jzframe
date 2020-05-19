package com.musikouyi.learning.countDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2019/3/8 20:49
 **/
public class NetworkHealthChecker extends BaseHealthChecker
{
    public NetworkHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}