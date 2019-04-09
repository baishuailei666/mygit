package learn;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/10/8 0008
 * @Desc :
 **/
public class test {
    public static void main(String[] args) {
         /*
         不借助第三方两个变量进行互换
        */
        int a = 10 ,b =50;
        System.out.println("不借助第三方两个变量进行互换前： a= " +a +", b= "+b);
        //方法一

        a= a+b;
        b= a-b;
        a= a-b;

        System.out.println("不借助第三方两个变量进行互换后： a= " +a +", b= "+b);
        System.out.println("--------------------------------------------------");
        /*
            方法二
        */

        System.out.println("不借助第三方两个变量进行互换前： a= " +a +", b= "+b);

        a = a ^ b;
        System.out.println(a);
        b = a ^ b;
        System.out.println(b);
        a = a ^ b;


        System.out.println("不借助第三方两个变量进行互换后： a= " +a +", b= "+b);


        System.out.println("-------------打印星星-------------");
        for(int i=6; i>0;i--){

            for(int j=i; j>0;j--){
                System.out.print("*");
            }
            System.out.println("");
        }

        System.out.println("-------------乘法表-------------");
        for(int i=1; i<10;i++){

            for(int j=i; j>0;j--){
                System.out.print(i + "*" + j + " = ");
                if(i*j<10){
                    System.out.print("0"+i*j);
                }
                else{
                    System.out.print(i*j);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }

        System.out.println("-------------选择排序-------------");
        int[] array = new int[] {43,656,754,887,112,2,41,512,1131,213,232,323,32,22,3,212,361};
        // 排序前
        System.out.println("排序前");
        printArray(array);
        selectSort(array, true);

        // 排序后
        System.out.println("排序后");
        printArray(array);

        System.out.println("-------------冒泡排序-------------");
        array = new int[] {43,656,754,887,112,2,41,512,1131,213,232,323,32,22,3,212,361};
        // 排序前
        System.out.println("排序前");
        printArray(array);
        BubbleSort(array, true);

        // 排序后
        System.out.println("排序后");
        printArray(array);


        System.out.println("-------------二分查找-------------");
        int [] arr =new int[] { 1,3,4,6,8,12,15,16,35,67,69,70,92};
        int index = -1;
        index = halfSearch2(arr,16);
        System.out.println("index: " + index);
    }

    /**
     * 选择排序，每一趟从待排序的元素中选出最小（或最大）的一个元素，
     * 顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完
     * @param arr
     * @param Desc 排序是否从大到小
     * @return
     */
    public static int[] selectSort(int[] arr, Boolean Desc) {
        System.out.println("............selectSort............");
        if (arr == null) {
            return arr;
        }
        if (Desc) {
            for (int i=0; i<arr.length-1; i++) {
                for (int j=i+1; j<arr.length; j++) {
                    if (arr[i] < arr[j]) {
                        swap(arr, i, j);
                    }
                }
                System.out.println("i=" + i + " ");
                printArray(arr);
            }
        } else {
            for (int i=0; i<arr.length-1; i++) {
                for (int j=i+1; j<arr.length; j++) {
                    if (arr[i]>arr[j]) {
                        swap(arr,i,j);
                    }
                }
            }
        }
        return arr;
    }

    /**
     * 冒泡排序：
     * 比较相邻的两个元素，如果第一个比第二个大，就交换他们两个；
     * 对每一对相邻的元素作相同的工作，从开始第一对到结尾的最后一对。
     * 在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，知道没有任何一个元素需要比较。
     * @param arr
     * @param Desc
     * @return
     */
    public static int[] BubbleSort(int[] arr, Boolean Desc) {
        System.out.println("............BubbleSort............");
        if (arr == null) {
            return arr;
        }
        if (Desc) {
            for (int i=0; i<arr.length-1; i++) {
                for (int j=0; j<arr.length-i-1; j++) {
                    if (arr[j]<arr[j+1]) {
                        swap(arr,j,j+1);
                    }
                }
                System.out.println("i=" + i + " ");
                printArray(arr);
            }
        } else {
            for (int i=0; i<arr.length-1; i++) {
                for (int j=0; j<arr.length-i-1; j++) {
                    if (arr[j] > arr[j+1]) {
                        swap(arr,j,j+1);
                    }
                }
            }
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            if (i<arr.length-1) {
                System.out.println(arr[i] + ",");
            } else {
                System.out.println(arr[i]);
            }
        }
        System.out.println("");
    }

    /**
     * 交换位置
     * @param arr
     * @param x
     * @param y
     */
    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;

    }


    /**
     * 二分查找
     * @param arr
     * @param key
     * @return
     */
    public static int halfSearch(int[] arr, int key) {
        int min,mid,max;
        min=0;
        max=arr.length-1;
        mid=(min+max)/2;

        while (arr[mid] != key) {
            if (arr[mid]>key) {
                max=mid-1;
            } else if (arr[mid]<key) {
                min=mid+1;
            } if (min>max) {
                return -1;
            }
            mid = (min + max) / 2;
        }
        return mid;
    }

    //二分查找
    public static int halfSearch2(int[] arr, int key)
    {
        int min = 0, max = arr.length-1, mid = 0;

        while(min <= max)
        {
            mid = (min+max) >> 1;

            if(arr[mid]<key)
                min = mid + 1;
            else if(arr[mid]>key)
                max = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
