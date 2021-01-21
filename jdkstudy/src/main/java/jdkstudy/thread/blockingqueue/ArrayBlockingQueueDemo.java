package jdkstudy.thread.blockingqueue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 一个使用ArrayBlockingQueue的生产者-消费者例子
 *
 */
public class ArrayBlockingQueueDemo {
  private static int threadNum = 20;
  private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(6000);

  private static AtomicLong taskNum = new AtomicLong(0);
  private static long start = System.currentTimeMillis();

  public static void main(String[] args) throws InterruptedException {
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 32, 3,
      TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(11196),
      new ThreadPoolExecutor.DiscardOldestPolicy());

    // 统计线程
    new Thread(() -> {
      while (true) {
        long allTask = taskNum.get();
        System.out.println(allTask
          + ", qps:" + (allTask / ((System.currentTimeMillis() - start) /1000 + 1))
          + ", queue size:" + queue.size());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    // 消费者
    new Thread(() -> {
      while (true) {
        List<Integer> list = new ArrayList<>();
        queue.drainTo(list, 64);
        if (list.size() > 0) {
          Future future = threadPool.submit(new ThreadPoolTask(list));
        } else {
          try {
            Thread.sleep(3);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();

    // 生产者
    List<Thread> allThread = new ArrayList<>();
    for (int i = 0 ; i < threadNum; i++) {
      Thread thread = new Thread(() ->{
        int num = 0;
        while (true) {
          queue.offer(1);
          num ++;
          if (num % 1000 == 0) {
            try {
              Thread.sleep(10);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      allThread.add(thread);
      thread.start();
    }
    for (Thread ele: allThread) {
      ele.join();
    }
  }

  public static class ThreadPoolTask implements Runnable, Serializable {
    private static final long serialVersionUID = 0;
    private List<Integer> list;

    @Override
    public void run() {
      int all = 0;
      for (Integer i: list) {
        all += i;
      }
//      try {
//        Thread.sleep(10);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
      taskNum.getAndAdd(all);
    }

    public ThreadPoolTask(List<Integer> list) {
      this.list = list;
    }
  }
}
