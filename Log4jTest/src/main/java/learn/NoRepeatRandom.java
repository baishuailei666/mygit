package learn;

import java.util.Random;
import java.util.TreeSet;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class NoRepeatRandom {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        Random ran = new Random();
        int count = 0;
        while (count < 20) {
            boolean succeed = set.add(ran.nextInt(100));
            if (succeed)
                count++;
        }
        int size = set.size();
        Integer[] array = new Integer[size];
        set.toArray(array);
        System.out.println("生成的随机不重复的数据如下：");
        for (int value : array) {
            System.out.print(value + "  ");
        }
    }
}
