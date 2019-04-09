package ftp;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class testTwo {
    static int dayInt;
    static long fileL;

    static int no = 0;
    static List<Long> list2;

    static long totalSize = 0;

    // 递归方法
    public static void deepList(File file) {
        if (file.isFile() || (0 == file.list().length)) {
            return;
        } else {
            File[] files = file.listFiles();
            System.out.println(files[0]);
            files = FileSort.noSort(files);
            System.out.println(files[0]);
            for (File f : files) {
                System.out.println(f);
                if (f.isFile()) {
                    String fileName = f.getName();
                    if (!fileName.equals("Log" + ".txt")) {
                        String fileDay = fileName.substring(3, 13);
                        System.out.println(fileDay);
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, -30);
                        Date date = cal.getTime();
                        Date d = new Date();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        System.out.println( " : "+ df.format(date));
//                        dayInt = (int) TypeConvertUtil.getIntervalDays(fileDay, df.format(d));
                        System.out.print("离今天有：" + dayInt + " 天了   ");
                        fileL = f.length();
                        System.out.println("f" + fileL);
                        list2 = new ArrayList<>();
                        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                        map.put(dayInt, no);
                        list2.add(map.get(dayInt), fileL);
                        for (int i = 0; i < list2.size(); i++) {
                            totalSize += list2.get(i);
                            System.out.println("总大小：" + totalSize + " Bytes " + (totalSize / 1024 / 1024) + " MB");
                            if (totalSize > 12312) {
                                f.delete();
                            }
                        }
                    }
                }

            }
        }

    }

    public static void main(String[] args) {
        File f = new File("D:\\logs");
        deepList(f);
    }
}
