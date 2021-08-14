package cn.medemede.leecode.demos;

public interface ThreadPool {
    void execute(Runnable job);

    void addWorksNum(int num);

    void removeWorksNum(int num);

    void shutDown();

    int getJobSize();

}
