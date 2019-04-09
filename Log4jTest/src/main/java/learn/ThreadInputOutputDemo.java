package learn;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/10/9 0009
 * @Desc :线程间通讯：其实就是多个线程在操作同一个资源，但是操作的动作不同。
 * wait()；线程存储在线程池中
 * notify()；唤醒线程池中的线程，通常唤醒顶部的线程
 * notifyAll()；唤醒线程池中所有线程
 *
 * 这些操作线程的方法要使用在同步中，因为要对持有监视器（锁）的线程操作。
 * 所以要是用在同步中，因为只有同步才具有锁。
 *
 * 为什么这些操作线程的方法要定义在Object类中呢？
 * 因为这些方法在操作同步中线程时，都必须要标识它们所操作线程只有的锁。
 * 只有同一个锁上的被wait()的线程，才可以被同一个锁上的notify()唤醒线程，不可以对不同锁中的线程唤醒
 * （也就是说等待和唤醒的线程必须是同一个锁）
 * 因为锁可以是任意对象，所以可以被任意对象调用的方法只能有Object，所以定义在Object类中。
 *
 **/
public class ThreadInputOutputDemo {
    public static void main(String[] args) {
        Res res = new Res();

        Input input = new Input(res);
        Output output = new Output(res);
        Thread t1 = new Thread(input);
        Thread t2 = new Thread(output);

        t1.start();
        t2.start();
    }
}

class Res {
    String name;
    String sex;
    boolean flag = true;
}

class Input implements Runnable{
    private Res res;
    Input(Res res) {
        this.res = res;
    }
    @Override
    public void run() {
        int i=0;
        while (true) {
            synchronized (res) {
                if (res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i%2 == 0) {
                    res.name ="TOM";
                    res.sex = "man";
                } else {
                    res.name = "JACK";
                    res.sex = "woman";
                }
                i++;

                res.flag = true;
                // 唤醒Output
                res.notify();
            }
        }
    }
}

class Output implements Runnable{
    private Res res;
    Output(Res res) {
        this.res = res;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                if (!res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.name + " , " + res.sex);
                res.flag = false;
                res.notify();
            }
        }
    }
}
