package learn;

import java.util.Scanner;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class PhoneNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入电话号码:");
        String phoneNumber = sc.nextLine();
        System.out.println(check(phoneNumber));
    }

    public static String check(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty())
            return "请输入电话号码";
        String regex = "^\\d{3}-?\\d{8}|\\d{4}-?\\d{8}$";
        if (phoneNumber.matches(regex))
            return phoneNumber + "是一个合法的电话号码！";
        else
            return phoneNumber + "不是一个合法的电话号码！";
    }

}
