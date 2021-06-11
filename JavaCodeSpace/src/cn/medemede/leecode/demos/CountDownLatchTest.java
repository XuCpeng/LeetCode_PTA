package cn.medemede.leecode.demos;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch不可重用，利用它可以实现类似计数器的功能。
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行。
 * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        countDownSubThread(latch);

        countDownSubThread(latch);

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void countDownSubThread(CountDownLatch latch) {
        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                // 关键设置
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
