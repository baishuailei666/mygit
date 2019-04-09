package LoginTest;

import java.util.Date;

/**
 * @author Administrator
 */
public class LastTimeDemo extends Thread {
    /**
     * 最近活跃的时间
     */
    public static Date lastActiveTime ;
    /**
     * 多长未活跃则提示
     */
    public static long remindTime;
    @Override
    public void run() {
        while(true){
            Date now = new Date();
            long nowTime = now.getTime();
            if (nowTime - lastActiveTime.getTime() > remindTime){
                System.out.println("未活跃");
                break;
            }else{
                try {
                    //设置定时时间为2000毫秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //设置上一次活跃时间
        lastActiveTime = new Date();
        //设置未活跃时间超过1000毫秒则提醒
        remindTime = 1000;
        LastTimeDemo thread = new LastTimeDemo();
        thread.start();
    }

}
