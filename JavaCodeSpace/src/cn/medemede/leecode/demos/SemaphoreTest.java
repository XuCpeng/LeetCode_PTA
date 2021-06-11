package cn.medemede.leecode.demos;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，
 * 通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * <p>
 * acquire,release会被堵塞，可使用tryAcquire
 */
public class SemaphoreTest {
    static Random r = new Random();

    public static void main(String[] args) {
        int n = 8;
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < n; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int id;
        private Semaphore semaphore;

        public Worker(int id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + this.id + "占用一个机器在生产...");
                Thread.sleep(r.nextInt(2500));
                System.out.println("工人" + this.id + "释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
