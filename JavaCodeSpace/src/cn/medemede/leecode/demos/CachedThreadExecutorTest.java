package cn.medemede.leecode.demos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CachedThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 20).forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                String threadName = Thread.currentThread().getName();
                System.out.println("finished " + i + ": " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        executorService.shutdown();

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executorService.isTerminated()) {
                executorService.shutdownNow();
            }
        }
    }
}
