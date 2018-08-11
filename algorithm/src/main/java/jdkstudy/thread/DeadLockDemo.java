/**
 * @(#)DeadLockDemo.java, 2016/04/11
 */
package jdkstudy.thread;

/**
 * 2016/04/11
 * @author zhaowei
 */
public class DeadLockDemo {
    private static String a = "1";
    private static String b = "2";

    public static void main(String[] args) {
        Thread aThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(a) {
                    try {
                        Thread.sleep(1000);
                        synchronized (b) {
                            System.out.println("a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread bThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(b) {
                    try {
                        Thread.sleep(1000);
                        synchronized (a) {
                            System.out.println("b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        aThread.start();
        bThread.start();
    }
}
