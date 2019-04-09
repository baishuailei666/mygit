package TimeTransform;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService中两种常用的调度方法
 * ScheduleAtFixedRate每次执行时间为上一次任务开始起向后推一个时间间隔，
 * 即每次执行时间为 :initialDelay, initialDelay+period, initialDelay+2*period, …；
 * ScheduleWithFixedDelay 每次执行时间为上一次任务结束起向后推一个时间间隔，
 * 即每次执行时间为：initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay。
 * ScheduleAtFixedRate 是基于固定时间间隔进行任务调度，
 * ScheduleWithFixedDelay 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度。
 *  @author Administrator
 */
public class TimeTask2Test {
    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("tot");
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行任务的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable,10,10, TimeUnit.SECONDS);


    }


//    public static void main(String[] args) {
//
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        for (int i = 0; i < 10; ++i) {
//            executor.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " run ");
//                }
//            }, 2, TimeUnit.SECONDS);
//        }
//        executor.shutdown();
//    }

//    public static void testExcuters(){
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
//
//        service.scheduleAtFixedRate(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("ScheduledExecutorService:测试开始");
//
//            }
//        }, 5, 3,TimeUnit.SECONDS);
//    }
}
