/**
 * @(#)ThreadPool.java
 */
package jdkstudy.thread.threadpool.mypool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2016/07/12
 * @author zhaowei
 */
public class ThreadPoolDemo implements ThreadPool {
    private int maxWorkerNum;
    private int minWorkerNum = 1;
    private final LinkedList<Job> jobList = new LinkedList<Job>();
    private final List<Worker> workerList = Collections.synchronizedList(new ArrayList<Worker>());

    private AtomicInteger threadNum = new AtomicInteger();
    private int workerNum;
    private  class Worker implements Runnable {
        private volatile boolean isRunning = true;
        public void run() {
            while (isRunning) {
                Job job = null;
                synchronized (jobList) {
                    while (jobList.isEmpty()) {
                        try {
                            jobList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobList.removeFirst();
                    if (job != null) {
                        try {
                            job.excute();//similar as run() if job impl runnable interface
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        public void shutdown() {
            isRunning = false;
        }
    }

    public ThreadPoolDemo(int maxWorkerNum) {
       this.maxWorkerNum = maxWorkerNum;
    }

    private void initWorkers(int workerNum) {
        for (int i = 0; i < workerNum; i++) {
            Worker worker = new Worker();
            workerList.add(worker);
            Thread thread = new Thread(worker, "thread_num" + threadNum.incrementAndGet());
            System.out.println("start a worker");
            thread.start();
        }
    }

    public void addWorker(int num) {
        synchronized (jobList) {
            if ( (num + workerNum) > maxWorkerNum) {
                num = maxWorkerNum - workerNum;
            }
            initWorkers(num);
            workerNum += num;
        }
    }

    public void removeWorker(int num) {
        if (num > workerList.size()) {
            throw new IllegalArgumentException("remove too many worker beyond now worker num");
        }
        synchronized (jobList) {
            for (int i = 0; i < num && workerList.size() >= minWorkerNum; i++) {
                Worker worker = workerList.get(0);
                if (workerList.remove(worker)) {
                    worker.shutdown();
                }
            }
            workerNum = workerList.size();
        }
    }

    public void execute(Job job) {
        if (job != null) {
            synchronized(jobList) {
                jobList.addLast(job);
                jobList.notify();
            }
        }
    }

    public void shutdown() {
        for (Worker worker: workerList) {
            worker.shutdown();
        }
    }

    public int getJobsNum() {
        return jobList.size();
    }

    public static void main(String[] args) {
        System.out.println("可用的cpu数:" + Runtime.getRuntime().availableProcessors());

        long start = System.currentTimeMillis();
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(5);
        threadPoolDemo.addWorker(5);
        for (int i = 0 ; i < 100; i++) {
            Job job = new TestJob();
            job.setName("job" + i);
            threadPoolDemo.execute(job);
        }
        long end = System.currentTimeMillis();
        long durationMultThread = (end - start);
        System.out.println("time cost in mult thread is:" + durationMultThread);

        start = System.currentTimeMillis();
        for (int i = 0 ; i < 100; i++) {
            Job job = new TestJob();
            job.setName("job" + i);
            job.excute();
        }
        end = System.currentTimeMillis();
        System.out.println("time cost in single thread is:" + (end - start));
        System.out.println("time cost in diff is(single - mult =):" + ((end - start) - durationMultThread));
    }
}
