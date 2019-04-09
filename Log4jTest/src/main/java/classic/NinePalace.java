package classic;

import java.util.Scanner;

/**
 * 在井字形的格局中(只能是奇数格局)，放入数字(数字由)，使每行每列以及斜角线的和都相等

 经验规则：从 1 开始按顺序逐个填写； 1  放在第一行的中间位置；下一个数往右上角45度处填写；
 如果单边越界则按头尾相接地填；如果有填写冲突，则填到刚才位置的底下一格；
 如果有两边越界，则填到刚才位置的底下一格。

 个人认为，可以先把最中间的数填到九宫格的最中间位置；再按上面的规则逐个填写，而且
 填的时候还可以把头尾对应的数填到对应的格子中。(第 n 个值跟倒数第 n 个值对应，格局上以最中
 间格为轴心对应)
 这样就可以同时填两个数，效率比之前更高；其正确性有待数学论证(但多次实验之后都没发现有错)。
 九宫格的 1 至少还可以填在另外的三个位置，只是接下来的填写顺序需要相应改变；
 再根据九宫格的对称性，至少可以有8种不同的填写方式
 * @author Administrator
 */
public class NinePalace {
    public static void main(String[] args){
        // 定义 N 为九宫格的行列数，需要输入
        System.out.println("请输入九宫格的行列规模(只能是奇数的)");
        Scanner n = new Scanner(System.in);
        int N;

        //判断格局是否奇数 （可判断出偶数、负数 及小数）
        double d;
        while (true){
            d = n.nextDouble();
            N = (int)d;
            if ((d-N)>1.0E-4||N%2==0||N<0)
            {System.out.println("输入出错,格局只能是正奇数。请重新输入");}
            else break;
        }

        //老师的九宫格填写方法
        int[][] result = new int[N][N];   //定义保存九宫格的数组
        int row = 0; //行 初始位置
        int col = N/2; //列 初始位置,因为列由0开始，故N/2是中间位置
        for (int i=1;  i<=N*N; i++){
            result [row][col] = i;
            row--;
            col++;
            if (row<0&&col>=N){col--;row+=2;} //行列都越界
            else if (row<0){ row = N-1;}   //行越界
            else if (col>=N){col = 0;}  //列越界
            else if (result[row][col] != 0){col--;row+=2;}  //有冲突
        }

        //打印出九宫格
        for (int i=0;  i<N;  i++){
            for(int j=0;  j<N; j++){System.out.print(result[i][j]+"\t");}
            System.out.println();
        }

        //我个人的填格方式
        int[][] result2 = new int[N][N];  //为免冲突，重新 new 一个数组
        result2[N/2][N/2] = (N*N+1)/2;  //先把中间值赋予中间位置
        row = 0;   //定义行及列的初始赋值位置。之前赋值的for对两个值有影响，故需重新定位
        col = N/2;
        for (int i=1; i<=N*N/2; i++){
            result2[row][col] = i;
            //下面这句是把跟 i 对应的值放到格局对应的位置上
            result2[N-row-1][N-col-1] = N*N+1-i;
            row--;
            col++;
            if (row<0){ row = N-1;}   //行越界
            else if (col>=N){col = 0;}  //列越界
            else if (result2[row][col] != 0){col--;row+=2;}  //有冲突
            //这方法不可能出现行列两边都越界的情况,详情需要数学论证
        }

        System.out.println();
        //再次打印出九宫格，以对比验证
        for (int i=0;  i<N;  i++){
            for(int j=0;  j<N; j++){System.out.print(result2[i][j]+"\t");}
            System.out.println();
        }

    }
}
