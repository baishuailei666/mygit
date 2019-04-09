package classic;

/**
 * @author Administrator
 */
public class MaoPao {
    public static void main(String args[])
    {
        int[] arr={2,1,3,4,6,5,7,8,9,0,10};
        //N是数组的元素个数，这样无论多少个数，直接修改arr中的元素就行了，
        //不需要调整循环次数
        int N = arr.length;
        int temp=0;
        //冒泡排序：每次把最大的放到最后，N-i是因为第i次排序之后，
        //数组arr的最后i个数已经是按照大小顺序的了，所以不需要再排序了
        //比如第一次排序之后，最后一个数肯定是最大的，下一次只需要排前9个就行了。
        for(int i=1;i<N;++i)
        {
            for(int j=0;j<N-i;++j)
            {
                //如果前面的数比后面的大，则不是按照顺序的，因此要交换
                if(arr[j]>arr[j+1])
                { //交换2个数
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        //输出排序后的结果
        for(int i=0;i<N;++i)
        {
            System.out.print(arr[i]+"  ");
        }

    }
}