package learn;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class CurrencyFormat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个货币数字：");
        double number = scan.nextDouble();
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println("Locale.CHINA:" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Locale.US:" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println("Locale.UK:" + format.format(number));
    }
}
