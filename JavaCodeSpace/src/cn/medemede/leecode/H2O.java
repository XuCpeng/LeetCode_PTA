package cn.medemede.leecode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1117. H2O 生成
 */
public class H2O {
    private final Semaphore h = new Semaphore(2);
    private final Semaphore o = new Semaphore(2);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }
}

class H2O2 {
    int hNum = 0;
    private ReentrantLock lock = new ReentrantLock();
    Condition oFull = lock.newCondition();
    Condition hFull = lock.newCondition();


    public H2O2() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hNum == 2) {
                hFull.signal();
                oFull.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hNum++;
            if (hNum == 2) {
                hFull.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (hNum <= 0) {
                oFull.signal();
                hFull.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hNum -= 2;
            if (hNum <= 0) {
                oFull.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
