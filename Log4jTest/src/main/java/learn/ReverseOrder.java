package learn;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class ReverseOrder {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; ++i)
            list.add(i);
        System.out.println("顺序输出：" + list);
        System.out.print("逆序输出：");
        ListIterator<Integer> li = list.listIterator();
        for (li = list.listIterator(); li.hasNext();) {
            li.next();
        }
        for (; li.hasPrevious();) {
            System.out.print(li.previous() + "  ");
        }
    }
}
