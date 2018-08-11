/**
 *
 * FutureDemo.java, 2017—01-02.
 *
 *
 */
package jdkstudy.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhaowei
 */
public class FutureDemo {
    private static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.currentThread().sleep(5000);
            return new Random().nextInt(10);
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
//        Thread thread = new Thread(futureTask);
//        thread.start();
//        System.out.println("started");
//        System.out.print(futureTask.get());

        //ExecutorService
        long start = System.currentTimeMillis();
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        Future<Integer> future = threadPoolExecutor.submit(new MyCallable());
        Thread.currentThread().sleep(6000);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println((end - start));

    }
}
