/**
 * @(#)TestJob.java
 */
package jdkstudy.thread.threadpool.mypool;

/**
 * 2016/07/17
 * @author zhaowei
 */
public class TestJob implements Job {
    private String name;
    @Override
    public void excute() {
        System.out.println(name + " real run in:" + Thread.currentThread().getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
