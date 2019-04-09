package classic;

/**
 * 二分查找
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/17 0017
 * @Desc :
 **/
public class ErFen {
    public static void main(String[] args) {
        int[] data = {2,1,3,4,6,5,7,8,9,0,10};
        System.out.println(ef(data, 0));
    }

    public static int ef(int a[], int tag) {
        int first = 0;
        int end = a.length;
        for (int i = 0; i<a.length; i++) {
            int mid = (first + end) / 2;
            if (tag == a[mid]) {
                return mid;
            }
            if (tag > a[mid]) {
                first = mid + 1;
            }
            if (tag < a[mid]) {
                end = mid + 1;
            }
        }
        return 0;
    }

}
