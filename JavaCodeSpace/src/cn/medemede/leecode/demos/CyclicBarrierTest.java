package cn.medemede.leecode.demos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 字面意思回环栅栏，CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 当调用await()方法之后，线程就处于barrier了。
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int n = 4;

        // 第二个参数为额外的Runable方法，都到达barrier后会挑选一个线程执行，若不能全部到达则无法执行
        CyclicBarrier barrier = new CyclicBarrier(n,
                () -> System.out.println("使用以下线程执行额外Runable：" + Thread.currentThread().getName()));

        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                try {
                    // 故意使最后一个线程延迟启动，使前面的线程等待超时
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Writer(barrier).start();
        }

    }

    static class Writer extends Thread {
        private final CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");

            try {
                Thread.sleep(3000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");

                // 关键设置，可设置等待其他线程的超时时间
                barrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
