package LoginTest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class TimeT {
    private final static String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) throws ParseException {
        Long Interval=(long) (10L  * 60L * 1000L);
        System.out.println(Interval);

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);

        Map<String,String> responseMap = new HashMap<>(1);
        String tokenSign = Jwt.sign(responseMap, 30L * 60L * 1000L);;
        if (tokenSign != null) {
            responseMap.put("token", tokenSign);
        }
        System.out.println(responseMap);

        // 转换成时间戳
        long timeSpan = new SimpleDateFormat(datetimeFormat).parse("2018-03-05 9:14:48").getTime();
        System.out.println(timeSpan);

        Date now = new Date();
        System.out.println("date" + now);
        long nowTime = now.getTime();
        System.out.println(nowTime);
        System.out.println(String.valueOf(nowTime));

        System.out.println(nowTime-timeSpan);



        long currentMillis = System.currentTimeMillis();
        System.out.println(currentMillis);

        System.out.println(currentMillis - 60000);

    }
}
