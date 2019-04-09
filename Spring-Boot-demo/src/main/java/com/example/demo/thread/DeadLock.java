package com.example.demo.thread;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/27 0027
 * @Desc :死锁：进程A中包含资源A，进程B中包含资源B，A的下一步需要资源B，B的下一步需要资源A，
 * 所以它们就互相等待对方占有的资源释放，所以也就产生了一个循环等待死锁。
 *
 * 死锁形成的必要条件（都满足之后就会产生）：
 *      1、互斥条件：资源不能被共享，只能被同一个进程使用
 *      2、请求与保持条件：已经得到资源的进程可以申请新的资源
 *      3、非剥夺条件：已经分配的资源不能从相应的进程中强制剥夺
 *      4、循环等待条件：系统中若干个进程形成环路，该环路中每个进程都在等待相邻进程占用的资源
 * 处理死锁的方法：
 *      1、忽略该问题，也即鸵鸟算法。当发生了什么问题时，不管他，直接跳过，无视它；
 *      2、检测死锁并恢复；
 *      3、资源进行动态分配；
 *      4、破除上面的四种死锁条件之一。
 **/
public class DeadLock {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockTest(true));
        Thread t2 = new Thread(new DeadLockTest(false));

        t1.start();
        t2.start();
    }
}

class DeadLockTest implements Runnable {
    private boolean flag;
    static Object o1 = new Object();
    static Object o2 = new Object();
    public DeadLockTest(boolean flag) {
        this.flag = flag;
    }
    @Override
    public void run() {
        if (flag) {
            synchronized (o1) {
                System.out.println("if lock1");
                synchronized (o2) {
                    System.out.println("if lock2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println("else lock2");
                synchronized (o1) {
                    System.out.println("else lock1");
                }
            }
        }

    }
}
