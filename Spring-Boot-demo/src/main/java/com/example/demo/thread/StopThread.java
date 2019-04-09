package com.example.demo.thread;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/27 0027
 * @Desc :
 **/
public class StopThread {
    public static void main(String[] args) {
        int num = 0;
        StopTh stopTh = new StopTh();
        Thread t1 = new Thread(stopTh);
        Thread t2 = new Thread(stopTh);

        t1.start();
        t2.start();

        while (true) {
            if (num++ == 50) {
                stopTh.flagChange();
                break;
            }
            System.out.println(Thread.currentThread().getName() + "..." + num);
        }

    }
}
class StopTh implements Runnable {
    private boolean flag = true;

    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "STOP RUN.");
        }
    }
    public void flagChange() {
        flag = false;
    }
}
