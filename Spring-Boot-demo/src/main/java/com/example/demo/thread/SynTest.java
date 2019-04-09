package com.example.demo.thread;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/27 0027
 * @Desc :线程同步和锁：
 *  为什么要进行线程同步？
 *      java运行多线程并发控制，当多个线程同时操作一个可共享资源变量时（对其进行增删改查操作），会导致数据不准确，而且相互之间
 *      产生冲突。所以加入同步锁比避免该线程在没有完成操作前被其他线程调用， 从而保证该变量的唯一性和准确性。
 *  不同步会发生的问题？
 *      在介绍同步方法之前先演示一个当多个线程操作一个共享资源时可能会发生的错误，这里用的方法是让线程在执行时睡眠10毫秒，
 *      会导致多个线程去操作同一个共享变量。
 *  同步方法一：
 *      同步函数：就是用synchronize关键字修饰的方法。因为每个java对象都有一个内置锁，当用synchronize关键字修饰方法时
 *      内置锁会保护整个方法，而在调用该方法之前，要先获得内置锁，否则就会处于阻塞状态。
 *  同步方法二：
 *      同步代码块：就是拥有synchronize关键字修改的语句块。被该关键字修饰的语句块会自动被加上内置锁，从而实现同步。
 **/
public class SynTest {
    public static void main(String[] args) {
        MySyn mySyn = new MySyn();
        Thread t1 = new Thread(mySyn, "t1输出：");
        Thread t2 = new Thread(mySyn, "t2输出：");
        Thread t3 = new Thread(mySyn, "t3输出：");
        t1.start();
        t2.start();
        t3.start();
    }
}
class MySyn implements Runnable {

    int tick = 10;

    @Override
    public void run() {
        while (true){
            // 如果同步函数被静态修饰之后，使用 类名.class，静态方法中不能使用this
            // 静态内存：内存中没有本类对象，但是一定有该类对应的字节码对象。所以静态的同步方法使用的锁是该方法所在类的字节码文件对象
            // 同步的前提：
            // 1、必须要有两个或者两个以上的线程
            // 2、必须是多个线程使用同一个锁
            // 3、必须保证同步中只能有一个线程在运行
            // 4、只能同步方法，不能同步变量和类
            // 5、不必同步类中的所有方法，类可以拥有同步和非同步的方法。
            // 6、如果一个线程在对象上获得一个锁，就没有任何其他线程进入（该对象）类中的任何一个同步方法。
            // 7、线程睡眠时，它所持有的任何锁都不会释放
            // 好处：解决了多线程的安全问题
            // 弊端：多个线程需要判断，消耗资源，降低效率
            //
            // 如何找到问题？
            // 1、明确哪些代码是多线程代码
            // 2、明确共享资源
            // 3、明确多线程代码中哪些代码是操作共享数据的。
            synchronized(this) {
                if (tick>0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "  " + tick--);
                }
            }

        }
    }
}
