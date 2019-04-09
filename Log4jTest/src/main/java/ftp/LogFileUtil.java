package ftp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志保存本地工具类
 * @author 白帅雷
 * @date 2018-05-31
 */
public class LogFileUtil {

    public static void main(String[] args) {
        saveLogFile("d:\\ 测试日志.log","今天是五月份的最后一天2008",2L);
    }

    public static void saveLogFile(String fileName, String data, long allowFileSize) {
        int kb = 1024;
        File f = new File(fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f.createNewFile();
            fw = new FileWriter(f, true);
            bw = new BufferedWriter(fw);
            if (f.length() < (allowFileSize )) {
                bw.write(data);
                bw.newLine();
            } else {
                f = new File("d:\\测试日志" + 1 + ".log");
                f.createNewFile();
                fw = new FileWriter(f,true);
                bw = new BufferedWriter(fw);
                bw.write(data);
                bw.newLine();
                System.out.println("111");
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        OutputStream os = null;
//        try {
//            // 1k的数据缓存
//            byte[] bs = new byte[1024];
//            int len;
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            os = new FileOutputStream(file.getPath() + File.separator + fileName);
//            if (file.length() <= allowFileSize) {
//                while ((len = inputStream.read(bs)) != -1) {
//                    os.write(bs, 0, len);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                assert os != null;
//                os.close();
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void createFile(File file) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(date);
        String str1 = file.getParent() + "\\";
        String str2 = str1.concat(formatDate);
        String nameDate = str2 + ".txt";

    }

    /**
     * 获取某文件或文件夹的存储大小
     *
     * @param file 文件名
     * @return 文件大小
     */
    public static double getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children) {
                    size += getDirSize(f);
                }
                return size;
            } else {
                //如果是文件则直接返回其大小,以“GB”为单位
                return (double) file.length() / 1024 / 1024 / 1024;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }

    /**
     * 获取文件的最后修改时间与当前时间的天数差
     *
     * @param file 文件名
     * @return 天数
     */
    public static String getFileDay(File file){
        // 返回文件最后修改时间，是一个long型毫秒数
        long time = file.lastModified();
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
        System.out.println(date);
        long day = (System.currentTimeMillis()-time) / (1000 * 60 * 60 * 24);
        System.out.println(day + " 天");
        return String.valueOf(day);
    }


}
