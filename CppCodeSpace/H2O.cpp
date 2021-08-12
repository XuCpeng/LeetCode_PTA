#include <mutex>
#include <condition_variable>

class H2O
{
private:
    int hNum;
    int oNum;
    std::mutex lock;
    std::condition_variable cv;

public:
    H2O()
    {
        hNum = 0;
        oNum = 0;
    }

    void hydrogen(function<void()> releaseHydrogen)
    {
        // 锁是为了保护线程从 "被唤醒 -> wait" 期间不被其他线程打断
        std::unique_lock<std::mutex> l(lock);
        cv.wait(l, [this]()
                { return hNum < 2; });
        // releaseHydrogen() outputs "H". Do not change or remove this line.
        releaseHydrogen();
        hNum++;
        if (hNum == 2 && oNum == 1)
        {
            hNum = 0;
            oNum = 0;
        }
        cv.notify_one();
    }

    void oxygen(function<void()> releaseOxygen)
    {
        std::unique_lock<std::mutex> l(lock);
        cv.wait(l, [this]()
                { return oNum < 1; });
        // releaseOxygen() outputs "O". Do not change or remove this line.
        releaseOxygen();
        oNum++;
        if (hNum + oNum == 3)
        {
            hNum = 0;
            oNum = 0;
        }
        cv.notify_one();
    }
};