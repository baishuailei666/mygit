package ftp;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File.length()仅仅用于获取某个文件的大小，单位为bytes
 * 如果需要获得某个文件夹下所有文件的大小，则需要用到递归，依次获得目录下文件的大小
 */
public class testThere {
    public static double getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位
                double size = (double) file.length() / 1024 / 1024;
                return size;

            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }

    public static String getFileDate(File file){
        // 返回文件最后修改时间，是一个long型毫秒数
        long time = file.lastModified();
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
        System.out.println(date);
        long date1 = (System.currentTimeMillis()-time)/(1000 * 60 * 60 * 24);
        System.out.println(date1 + " 天");
        return date;
    }

    public static void main(String[] args) {
        double totalSize = getDirSize(new File("D:\\logs"));
        BigDecimal bd = new BigDecimal(totalSize);
        double f1 = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(totalSize + " MB");
        System.out.println(f1 + " MB");
        getFileDate(new File("D:\\Launcher\\logs\\ESlog.log"));
        File f = new File("D:\\Launcher\\logs\\UUIDout.txt");
        System.out.println("文件大小kb: " + (double) f.length() / 1024 / 1024);
    }
}
