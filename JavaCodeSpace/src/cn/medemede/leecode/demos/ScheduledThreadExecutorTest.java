package cn.medemede.leecode.demos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.println(System.currentTimeMillis()),
                1000, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(4999);
        scheduledExecutorService.shutdown();
        try {
            scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!scheduledExecutorService.isTerminated()) {
                scheduledExecutorService.shutdownNow();
            }
        }
    }
}
