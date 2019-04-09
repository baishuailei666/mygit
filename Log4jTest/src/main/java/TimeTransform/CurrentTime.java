package TimeTransform;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 */
public class CurrentTime {

    public Timestamp getDayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        return new Timestamp(cal.getTimeInMillis());
    }

    public Timestamp getDayBeginTimestamp() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000);
        return new Timestamp(date2.getTime());
    }

    public static void main(String[] args) throws ParseException {
        //当前0点时间戳
        System.out.println(new CurrentTime().getDayBegin());
        // 当天0点时间戳
        System.out.println(new CurrentTime().getDayBeginTimestamp());
        // 当前时间戳
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date()));
        System.out.println(Calendar.getInstance().getTime());
        // 当前时间戳
        System.out.println(new Timestamp(System.currentTimeMillis()));

    }

}