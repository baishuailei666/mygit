package learn;

import java.util.Date;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
class ThreadX implements Runnable {
    private Date runDate;

    @SuppressWarnings("deprecation")
    public void run() {
        System.out.println("线程X已经启动...");
        this.runDate = new Date();
        System.out.println("启动时间：" + runDate.toLocaleString());
    }
}

class ThreadY extends Thread {
    private boolean isRunState = false;

    public void start() {
        this.isRunState = true;
        super.start();
    }

    public void run() {
        int i = 0;
        try {
            while (isRunState) {
                this.setName("Thread-" + i++);
                System.out.println("线程Y" + this.getName() + "正在运行");
                Thread.sleep(200);
            }
        } catch (Exception e) {
        }
        System.out.println(this.getName() + "结束运行...");
    }

    public void setRunning(boolean isRunState) {
        this.isRunState = isRunState;
    }

    public void startThreadY() {
        System.out.println("启动线程Y...");
        this.start();
    }

    public void stopThreadY() {
        System.out.println("结束线程Y...");
        this.setRunning(false);
    }
}

public class StartThread {
    public void startX() {
        Runnable runnX = new ThreadX();
        Thread threadX = new Thread(runnX);
        threadX.start();
    }

    public void startY() {
        ThreadY ty = new ThreadY();
        ty.startThreadY();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ty.stopThreadY();
    }

    public static void main(String[] args) {
        StartThread test = new StartThread();
        test.startX();
        test.startY();
    }
}
