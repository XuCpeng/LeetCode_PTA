package cn.medemede.leecode.demos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepLiveTime = 60 * 5;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int workQueueCapacity = 100;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepLiveTime,
                timeUnit,
                new ArrayBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
