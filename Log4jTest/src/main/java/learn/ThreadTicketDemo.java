package learn;


/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/10/9 0009
 * @Desc :简单的买票程序，多个窗口同时买票。
 * 创建线程的第二种方法，实现Runnable接口
 * 步骤：
 * 1、定义类实现Runnable接口
 * 2、覆盖Runnable接口run()方法，将线程要运行的代码放在run()中
 * 3、通过Thread类建立线程对象
 * 4、将Runnable接口的子类对象作为实际参数给Thread类的构造函数
 *    自定义的run()方法所属的对象是Runnable接口的子类对象，所以要让线程调用指定对象的run方法，就必须明确该run方法的对象。
 * 5、调用Thread类的start方法开启线程并调用Runnable接口子类的run方法。
 *
 * 两种方式的区别：
 * Runnable方式：
 *      1、避免的单继承的局限性，在定义线程是建议使用该方法
 *      2、线程代码存在Runnable子类的run方法中
 * 继承Thread方式;
 *      1、有局限性，因为java的单继承特性，如果继承Thread类限制了继承其他类
 *      2、线程代码存放在Thread子类的run方法中
 **/
public class ThreadTicketDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        // 多个窗口
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

        Ticket2 t2 = new Ticket2();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();

        // 同步函数
        Ticket3 t3 = new Ticket3();
        Thread t31 = new Thread(t3);
        Thread t32 = new Thread(t3);
        t31.start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t3.flag=false;
        t32.start();

        // 同步函数被static修饰
        Ticket4 t4 = new Ticket4();
        Thread t41 = new Thread(t4);
        Thread t42 = new Thread(t4);
        t41.start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t4.flag=false;
        t42.start();

        // 死锁
        Ticket5 t5 = new Ticket5();
        Thread t51 = new Thread(t5);
        Thread t52 = new Thread(t5);
        t51.start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t5.flag=false;
        t52.start();
    }
}

class Ticket implements Runnable{

    private int tick = 10;
    @Override
    public void run() {
        while (true) {
            if (tick>0) {
                /*
                try
                {
                    Thread.sleep(10);
                }
                catch (Exception e)
                {
                }
                */

                /*
                通过上面代码Thread.sleep(10)；分析发现，打印出现0 -1 -2等错票
                多线程的运行出现的安全问题。
                问题的原因：
                    当多条语句在操作用一个线程共享数据时，
                    一个线程对多条语句执行了一部分，还没有执行完成时，
                    另一个线程参与进来执行，导致共享数据的错误。
                解决方法：
                    对于多条操作的共享数据语句，只能让一个线程执行完毕。
                    在一个线程执行过程中，其他线程不可以参与执行。

                java对于多线程的安全问题提供了一种同步代码用于解决这个问题
                synchronized(...)
                对象如同锁，持有锁的线程可以再同步执行，没有持有锁的线程即使获取cpu的执行权，也进不去，因为没有锁

                同步的前提：
                1、必须要有两个或以上的线程
                2、必须是多个线程使用同一个锁

                同步的利弊：
                利：保证只有一个线程在执行，解决了多线程中多条语句在操作同一个线程共享数据时的安全问题
                弊：多个线程需要判断锁，较为消耗资源


                */

                System.out.println(Thread.currentThread().getName()+" Sale :"+ tick --);
            }
        }
    }
}

class Ticket2 implements Runnable{

    private int tick = 10;
    Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (tick>0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" Sale :"+ tick --);
                }
            }
        }
    }
}


/**
 * 同步函数用的是哪一个锁呢？
 *      函数需要被对象调用，那么函数都有一个所属对象引用，就是this
 *      所以同步函数使用的锁是this
 *
 *      下面通过该程序进行验证：
 *      使用现场来买票
 *      一个线程在同步代码中，一个在同步函数中。都在执行卖票动作
 */
class Ticket3 implements Runnable{
    private int tick = 100;
    Object obj = new Object();
    boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (this) {
                    if (tick>0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" code Sale :"+ tick --);
                    }
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    public synchronized void show() {
        if (tick>0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" code Sale :"+ tick --);
        }
    }
}

/**
 * 如果同步函数被static修饰后，会使用的锁是什么呢？
 * 静态进内存时，内存中没有本类对象，但是一定有该类对应的字节码文件对象。
 * 类名.class 该对象的类型是class
 * 所以
 * 静态的同步方法，使用的锁是该方法所在类的字节码文件对象。类名.class
 */
class Ticket4 implements Runnable{
    private static int tick = 100;
    Object obj = new Object();
    boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (Ticket4.class) {
                    if (tick>0) {
                        try {
                            Thread.sleep(15);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" code Sale :"+ tick --);
                    }
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    public static synchronized void show() {
        if (tick>0) {
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" code Sale :"+ tick --);
        }
    }
}

/**
 * 死锁
 * 同步中嵌套同步
 */
class Ticket5 implements Runnable{
    private static int tick = 100;
    Object obj = new Object();
    boolean flag = true;
    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (Ticket5.class) {
                    show();
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    public static synchronized void show(){
        synchronized (Ticket5.class) {
            if (tick>0) {
                try{Thread.sleep(20);}catch (Exception e){e.printStackTrace();}
                //
                System.out.println(Thread.currentThread().getName()+" code Sale :"+ tick --);
            }
        }
    }
}


/**
 * 单例设计模式
 * 饿汉式
 */
class Single {
    private static final Single s = new Single();

    private Single() {

    }

    public static Single getInstance() {
        return s;
    }
}

/**
 * 单例设计模式
 * 懒汉式
 */
class Single1 {
    private static Single1 s = null;
    private Single1() {

    }
    public static Single1 getInstance() {
        // 双重判断
        if (s == null) {
            synchronized (Single1.class){
                if (s == null) {
                    s = new Single1();
                }
            }
        }
        return s;
    }
}