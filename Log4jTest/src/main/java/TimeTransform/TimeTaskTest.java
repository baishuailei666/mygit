package TimeTransform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 在实现时，Timer类可以调度任务,TimerTask则是通过在run()方法里实现具体任务
 * Timer实例可以调度多任务，它是线程安全的，
 * 当Timer的构造器被调用时，它创建一个线程，这个线程可以用来调度任务
 * @author Administrator
 */
public class TimeTaskTest {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("**");
            }
        };
        Timer timer = new Timer();
        // 执行任务的延迟时间
        long delay = 5*1000;
        // 执行任务的间隔时间
        long intevalPeriod = 3*1000;
        timer.scheduleAtFixedRate(task,delay,intevalPeriod);
    }
}
