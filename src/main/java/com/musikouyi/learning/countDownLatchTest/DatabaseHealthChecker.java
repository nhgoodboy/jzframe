package com.musikouyi.learning.countDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2019/3/8 20:51
 **/
public class DatabaseHealthChecker extends BaseHealthChecker
{
    public DatabaseHealthChecker (CountDownLatch latch)  {
        super("Database Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
