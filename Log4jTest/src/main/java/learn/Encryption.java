package learn;

import java.util.Scanner;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class Encryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----");
        String password = sc.nextLine();
        char[] array = password.toCharArray();
        for (int i=0; i<array.length; i++) {
            array[i] = (char)(array[i]^20000);
        }
        System.out.println("+++++");
        System.out.println(new String(array));
    }
}
