package jdkstudy.thread.threadpool.mypool;

/**
 * 2016/07/12
 *
 * @author zhaowei
 */
public interface ThreadPool {
    void addWorker(int num);
    void removeWorker(int num);
    void execute(Job job);
    void shutdown();
    int getJobsNum();
}
