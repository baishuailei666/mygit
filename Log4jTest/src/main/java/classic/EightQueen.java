package classic;


import java.util.*;

class Point{
        int x , y;
        public Point(int x , int y){
            this.x = x;
            this.y = y;
        }

        public Point copy(){
            Point point = new Point(x,y);
            return point;
        }

        @Override
        public String toString() {
            return x + " , " + y;
        }
    }
/**
 * 问题：
 * 八皇后问题是一个古老而著名的问题，该问题是十九世纪著名的数学家高斯1850年提出的。
 * 在国际象棋中，皇后是最有权利的一个棋子；只要别的棋子在它的同一行或同一列或同一斜线（正斜线或反斜线）上时，
 * 它就能把对方棋子吃掉。所以高斯提出了一个问题：在8*8的格的国际象棋上摆放八个皇后，使其不能相互攻击，
 * 即任意两个皇后都不能处于同一列、同一行、或同一条斜线上面，问共有多少种解法。
 *
 * 方法：
 * 简单说明一下思路。已知一个棋盘，棋子的放置范围为棋盘上的格子，
 * 另有四个限制条件：不同行，不同列，不同正斜线，不同反斜线。
 * 四个条件中，前两个条件比较简单，随便拿第一个作为先决条件，
 * 第二个为尝试条件，两个条件已经固定棋子的位置，
 * 所以第三个第四个就可认为是多余条件（就是为了提高问题难度而设置的条件）；
 * 即放棋子时先看行，一行一个的放，在行中放置过程中尝试不同的列。
 * 在行放置过程中检查另外三个条件是否满足，满足就继续放，不满足就回退，尝试。
 * 前两个条件很好判断。在行从上往下递增列从左到右的坐标系下同一个正斜线上的点行和列的和相等。
 * 在行从上往下递增列从右到左递增的坐标系下同以反斜线上的点。和列的和相等。
 * 而同一个点的坐标在两个坐标系中很好计算（可以直接看出来）。
 * @author Administrator
 */
public class EightQueen {

    static int startRow=0;
    static int endRow=7;
    public static void main(String[] args){
        List<Stack<Point>> list=new ArrayList<Stack<Point>>();
        Map<Integer, Integer> rowMap=new HashMap<Integer, Integer>();
        Map<Integer, Integer> colMap=new HashMap<Integer, Integer>();
        Map<Integer, Integer> positiveMap=new HashMap<Integer, Integer>();
        Map<Integer, Integer> negtiveMap=new HashMap<Integer, Integer>();
        Stack<Point> stack=new Stack<Point>();
        placeQueen(list, stack, rowMap, colMap, positiveMap, negtiveMap, startRow, endRow);
        System.out.println(list.size());
        for (Stack<Point> stack2 : list) {
            System.out.println("-----------------------------------------");
            for (Point point : stack2) {
                System.out.println(point);
            }
        }
        System.out.println("count: " + list.size());
    }
    static int count=0;
    public static int placeQueen(List<Stack<Point>> list,Stack<Point> stack,Map<Integer, Integer> rowMap,Map<Integer, Integer> colMap,Map<Integer, Integer> positiveMap,Map<Integer, Integer> negtiveMap,int currentRow,int aimRow){
        for(int i=0;i<=aimRow;i++){
            Integer countRow = rowMap.get(currentRow);
            Integer countCol=colMap.get(i);
            Integer countP=positiveMap.get(currentRow+i);
            Integer countN=negtiveMap.get(currentRow+aimRow-i);
            if(countRow==null&&countCol==null&&countP==null&&countN==null){
                rowMap.put(currentRow, 1);
                colMap.put(i, 1);
                positiveMap.put(currentRow+i, 1);
                negtiveMap.put(currentRow+aimRow-i, 1);
                if(currentRow==aimRow){
                    Point point=new Point(currentRow, i);
                    stack.push(point);
                    Stack<Point> stack2=new Stack<Point>();
                    for(int j=0;j<stack.size();j++){
                        stack2.add(stack.get(j).copy());
                    }
                    list.add(stack2);
                    stack.pop();
                    rowMap.put(currentRow, null);
                    colMap.put(i, null);
                    positiveMap.put(currentRow+i, null);
                    negtiveMap.put(currentRow+aimRow-i, null);
                    return 0;
                }
                Point p=new Point(currentRow, i);
                stack.push(p);
                int result=placeQueen(list, stack,rowMap,  colMap, positiveMap, negtiveMap, currentRow+1, aimRow);
                if(result<1){
                    stack.pop();
                    rowMap.put(currentRow, null);
                    colMap.put(i, null);
                    positiveMap.put(currentRow+i, null);
                    negtiveMap.put(currentRow+aimRow-i, null);
                }
            }

        }

        return -1;
    }

}
