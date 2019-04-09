package learn;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/10/9 0009
 * @Desc :创建两个线程和主线程交替运行；通过this.getName()或Thread.currentThread()可以获取线程名
 **/
public class ThreadTestDemo {
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        t1.start();

        Test2 t2 = new Test2();
        t2.start();

        Test3 t3 = new Test3();
        t3.start();

        for (int i=0; i<30; i++) {
            System.out.println("---main run---" + i);
        }
    }
}


class Test1 extends Thread {
    public void run() {
        for (int i=0; i<30; i++) {
            System.out.println(getName() + "---test1 run---" + i);
        }
    }
}

class Test2 extends Thread {
    public void run() {
        for (int i=0; i<30; i++) {
            System.out.println(getName() + "---test2 run---" + i);
        }
    }
}

class Test3 extends Thread {
    public void run() {
        for (int i=0; i<30; i++) {
            System.out.println(getName() + "---test3 run---" + i);
        }
    }
}