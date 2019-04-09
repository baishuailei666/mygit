package TimeTransform;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareDate {
    public static void main(String args[]) {
        int i= compare_date("1995-11-12", "1999-12-11 09:59");
        System.out.println("i=="+i);
    }

    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df1.parse(DATE1);
            Date dt2 = df2.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
