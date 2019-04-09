package TimerTest;


import java.util.concurrent.*;

/**
 * 这个例子有两个任务，第一个任务是每隔一秒打印一句“Task repeating.”；第二个任务是在5秒钟的时候取消第一个任务。
 * @author Administrator
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) throws Exception{
        // 1初始化一个ScheduleExecutorService对象，这个对象的线程池大小为2
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        // 2用内函数的方法定义一个Runnable任务
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task repeating...");
            }
        };

        /**
         * 3调用所定义的ScheduledExecutorService对象来执行任务，任务每秒执行一次。能重复执行的任务一定是Runnable类型。
         * 注意我们可以使用TimeUnit来指定时间单位，这也是java5.0里新的特征，5.0以前的计时单位是毫秒，现在可精确到奈秒
         */
        final ScheduledFuture future = executorService.scheduleAtFixedRate(task1,0,1, TimeUnit.SECONDS);

        // 4调用ScheduledExecutorService对象来执行第二个任务，第二个任务所作的就是在5秒钟的时候取消第一个任务
        ScheduledFuture future1 = executorService.schedule(new Callable() {
            @Override
            public String call() throws Exception {
                future.cancel(true);
                return "task cancelled!";
            }
        },10,TimeUnit.SECONDS);
        System.out.println(future1.get());

        // 5关闭服务
        executorService.shutdown();
    }

//    //初始化一个ScheduleExecutorService对象，这个对象的线程池大小为2
//    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//    //用内函数的方法定义一个Runnable任务
//    Runnable task = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                // 推送失败的告警事件存放在一个list里面
//                List<AlertEvent> alertEventRest = new ArrayList<>();
//                alertEventRest.add(alertEventService.getEventByEventId(alertEvent.getEventId()));
//                AlertEvent alertEventStatus = new AlertEvent();
//                alertEventStatus.setEventStartTime(alertEvent.getEventStartTime());
//                for (int i=0;i<alertEventRest.size();i++) {
//                    // 推送失败的告警事件存放在一个list里面，再遍历list
//                    int againRestRet = alertRestTemplate.postForObject(alertEventRest.get(i));
//                    if (againRestRet == 200) {
//                        alertEventStatus.setEventPushStatus("已推送");
//                        alertEventService.updateEventPushStatus(alertEventStatus);
//                    }
//                }
//            } catch (Exception e) {
//                logger.error(e);
//            }
//        }
//    };
//    /**
//     * 调用所定义的ScheduledExecutorService对象来执行任务，任务每30秒执行一次。能重复执行的任务一定是Runnable类型。
//     * 注意我们可以使用TimeUnit来指定时间单位，这也是java5.0里新的特征，5.0以前的计时单位是毫秒，现在可精确到奈秒
//     */
//    final ScheduledFuture future = executorService.scheduleAtFixedRate(task,0,30, TimeUnit.SECONDS);
//
//    // 调用ScheduleedExecutorService对象来执行第二个任务，即在600秒钟时候取消第一个任务
//            executorService.schedule(new Callable() {
//        @Override
//        public String call() throws Exception {
//            future.cancel(true);
//            return "success!";
//        }
//    },600,TimeUnit.SECONDS);
//    // 关闭服务
//            executorService.shutdown();
}
