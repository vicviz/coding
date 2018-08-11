/**
 *
 * ThreadPoolExecutorDemo.java, 2017—01-02.
 *
 *
 */
package jdkstudy.thread.threadpool.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 对比ThreadPoolExecutor的时间,与系统开多个线程开搞是不一样的
 * @author zhaowei
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        long threadCost = 50;
        long threadNum= 1000;
        long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 200, 60,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(20));
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0 ; i < threadNum;i++) {
            try {
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.print(1);
                            Thread.currentThread().sleep(threadCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1, TimeUnit.DAYS);
        long end = System.currentTimeMillis();
        long duration1 = end - start;
        System.out.println("mult thread in pool cost :" + duration1);

        start = System.currentTimeMillis();
        List<Thread> list = new ArrayList<>(100);
        for (int i = 0 ; i < threadNum;i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.print(2);
                        Thread.currentThread().sleep(threadCost);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            list.add(thread);
        }
        for (Thread thread: list) {
            thread.join();
        }
        end = System.currentTimeMillis();
        long duration2 = end - start;
        System.out.println("mult thread in cost :" + (duration2));
        System.out.println("(mult thread cost - in pool cost) = :" + (duration2 - duration1));
    }
}
