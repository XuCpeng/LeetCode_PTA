package cn.medemede.leecode.demos;

public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

    void addWorksNum(int num);

    void removeWorksNum(int num);

    void shutDown();

    int getJobSize();

}
