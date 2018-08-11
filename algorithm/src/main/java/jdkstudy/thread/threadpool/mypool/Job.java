package jdkstudy.thread.threadpool.mypool;

/**
 * 2016/07/17
 *
 * @author zhaowei
 */
public interface Job {
    public void excute();
    public void setName(String name);
    public String getName();
}
