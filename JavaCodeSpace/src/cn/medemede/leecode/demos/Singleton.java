package cn.medemede.leecode.demos;

/**
 * 双重校验锁实现对象单例（线程安全）
 */
public class Singleton {
    public volatile static Singleton uniqueInstance;

    public Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
