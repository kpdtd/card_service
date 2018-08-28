package com.anl.user;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yangyiqiang on 2018/8/27.
 */
public class Aaaa {
    static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService flowDailyPool = Executors.newFixedThreadPool(200);//日流量查询
        CountDownLatch nThread = new CountDownLatch(100); // 计数器
        int bb=0;
            for(int i=0;i<100;i++){
                Future<Boolean> bool = flowDailyPool.submit(() -> {
                    Thread.sleep(new Random().nextInt(10));
                    return true;
                });
                try {
                    if (bool.get()) {
                        Thread.sleep(100);
                        num++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    nThread.countDown();
                }
                bb=bb+1;
            }
        nThread.await(5, TimeUnit.SECONDS);
        System.out.println(num);
        System.out.println(bb);
    }
}
