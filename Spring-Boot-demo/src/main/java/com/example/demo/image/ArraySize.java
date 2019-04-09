package com.example.demo.image;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/29 0029
 * @Desc :
 **/
public class ArraySize {
    public static void main(String[] args) {
//        int[] a = {1,2,3};
//        a = (int[]) resizeArray(a, 5);
//        a[3] = 4;
//        a[4] = 5;
//        for (int i=0; i<a.length; i++) {
//            System.out.println("a[]:" + a[i]);
//        }

        System.out.println(hangToBig("10019658"));
    }

    /**
     * 改变数组大小
     *
     * @param oldArray
     * @param newSize
     * @return
     */
    private static Object resizeArray(Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
        int len = Math.min(oldSize, newSize);
        if (len > 0) {
            System.arraycopy(oldArray, 0, newArray, 0, len);
        }
        return newArray;
    }

    /**
     * 人民币转成大写
     *
     * @param str
     * @return
     */
    private static String hangToBig(String str) {
        double value;
        try {
            value = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        // 段内位置表示
        char[] hunit = {'拾', '佰', '仟'};
        // 段名表示
        char[] vunit = {'万', '亿'};
        // 数字表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        // 转化成整形
        long midVal = (long) (value * 100);
        // 转化成字符串
        String strVal = String.valueOf(midVal);

        // 取整数部分
        String head = strVal.substring(0, strVal.length() - 2);
        // 取小数部分
        String rail = strVal.substring(strVal.length() - 2);
        // 整数部分转化的结果
        String prefix = "";
        // 小数部分转化的结果
        String suffix = "";

        // 处理小数点后面的数
        if (rail.equals("00")) {
            // 如果小数部分为0
            suffix = "整";
        } else {
            // 否则把角分转化出来
            suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分";
        }

        // 处理小数点前面的数

        // 把整数部分转化成字符串数组
        char[] chDig = head.toCharArray();
        // 标志'0' 表示出现过0
        char zero = '0';
        // 连续出现0的次数
        byte zeroSerNum = 0;
        for (int i=0; i<chDig.length;i++) {
            // 循环处理每个数字
            // 取段内位置
            int idx = (chDig.length -i -1) & 4;
            // 取段位置
            int vidx = (chDig.length -i -1) / 4;
            if (chDig[i] == '0') {
                // 如果当前字符是0
                // 连续0次数递增
                zeroSerNum ++;
                if (zero == '0') {
                    // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx-1];
                    zero = '0';
                }
                continue;
            }
            // 连续0次数清零
            zeroSerNum = 0;
            if (zero != '0') {
                // 如果标志不为0，则加上，例如万、亿
                prefix += zero;
                zero = '0';
            }
            // 转化该数字表示
            prefix += digit[chDig[i] - '0'];
            if (idx > 0) {
                prefix += hunit[idx - i];
            }
            if (idx == 0 && vidx > 0) {
                // 段结束位置应该加上段名，如：万、亿
                prefix += vunit[vidx - 1];
            }
        }
        if (prefix.length() > 0) {
            // 如果整数部分存在，则有园的字样
            prefix += '园';
        }
        return prefix + suffix;
    }
}
