package ftp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class calender {
    public static void main(String[] args) throws ParseException {

        Date date=new Date();

        SimpleDateFormat dateFm = new SimpleDateFormat("MMMM");

        System.out.println(dateFm.format(date));

        getWeekOfDate(date);

        test();

//        cal();

        getSecondsNextEarlyMorning();
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        System.out.println(weekDays[w]);
        return weekDays[w];
    }

    public static void test() throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date= myFormatter.parse("2018-05-02");
        java.util.Date mydate= myFormatter.parse("2018-05-01");
        long day=(date.getTime()-mydate.getTime())/(24*60*60*1000);
        System.out.println(day);
    }


    public static void cal() throws ParseException {
        String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
        Calendar calendar = Calendar.getInstance();
        while(true){
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if(hour >= 9 && hour < 18){//每天从9点到18点，你自己改
                // 转换成时间戳
                long timeSpan = new SimpleDateFormat(datetimeFormat).parse("2018-03-05 9:14:48").getTime();
                System.out.println(timeSpan);
                System.out.println(hour);//这里添加抓取和保存数据的代码
                try {
                    Thread.sleep(10*60*1000);//多长时间抓取一次数据
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long seconds = (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        System.out.println(seconds);
        return seconds.longValue();
    }

}
