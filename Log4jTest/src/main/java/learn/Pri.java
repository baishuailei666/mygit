package learn;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
class Clicker extends Thread {
    private int click = 0;
    private volatile boolean running = true;

    public int getClick() {
        return click;
    }

    public void run() {
        while (running)
            click += 1;
    }

    public void normalStop() {
        running = true;
    }
}

public class Pri {
    public static void main(String[] args) {
        Clicker trHigh, trLow;
        trHigh = new Clicker();
        trLow = new Clicker();
        trHigh.setPriority(Thread.NORM_PRIORITY + 2);
        trLow.setPriority(Thread.NORM_PRIORITY - 2);
        trLow.start();
        trHigh.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        trHigh.normalStop();
        trLow.normalStop();
        try {
            trHigh.join();
            trLow.join();
        } catch (InterruptedException e) {
        }
        System.out.println("trHigh的循环次数为：" + trHigh.getClick());
        System.out.println("trLow的循环次数为：" + trLow.getClick());
    }
}