package cn.medemede.leecode.demos;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolImpl<Job extends Runnable> implements ThreadPool<Job> {
    /**
     * 工作者线程的最大数量
     */
    private static final int MAX_WORKER_NUMBERS = 30;

    /**
     * 工作者线程的默认工作数量
     */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /**
     * 工作者线程的最小数量
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 维护一个工作列表,里面加入客户端发起的工作
     */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作者线程的列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    /**
     * 工作者线程的数量
     */
    private int workerNum;
    /**
     * 每个工作者线程编号生成
     */
    private AtomicLong threadNum = new AtomicLong();

    class Worker implements Runnable {
        // 表示是否运行该worker
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                //线程的等待/通知机制
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            //线程等待唤醒
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对该线程的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 取出一个job
                    job = jobs.removeFirst();
                }
                //执行job
                if (job != null) {
                    job.run();
                }
            }
        }

        /**
         * 终止该线程
         */
        public void shutdown() {
            running = false;
        }
    }


    public ThreadPoolImpl(int num) {
        this.workerNum = Math.min(num, MAX_WORKER_NUMBERS);
        initializeWorkers(num);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            new Thread(worker).start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job == null) {
            throw new NullPointerException();
        }
        synchronized (jobs) {
            jobs.addLast(job);
            jobs.notify();
        }
    }

    @Override
    public void addWorksNum(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorksNum(int num) {
        synchronized (jobs) {
            if (this.workerNum - num < 0) {
                num = this.workerNum;
            }
            this.workerNum -= num;
            Iterator<Worker> iterator = workers.listIterator();
            while (num > 0) {
                Worker worker = iterator.next();
                worker.shutdown();
                iterator.remove();
                num--;
            }
        }
    }

    @Override
    public void shutDown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public int getJobSize() {
        return workers.size();
    }
}
