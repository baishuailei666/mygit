package learn;

import java.util.Scanner;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class Sudoku {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入要计算的数独维数(大于1的奇数):");
        int x = s.nextInt();
        int h = 0;
        int l = x / 2;
        int[][] a = new int[x][x];
        for (int i = 1; i <= x * x; i++) {
            a[h][l] = i;
            h--;
            l++;
            if (h < 0 && l >= x) {
                h += 2;
                l--;
            } else if (h < 0) {
                h = x - 1;
            } else if (l >= x) {
                l = 0;
            } else if (a[h][l] > 0) {
                h += 2;
                l--;
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
    }
}
