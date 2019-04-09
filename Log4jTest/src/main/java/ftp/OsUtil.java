package ftp;

import java.io.File;

public class OsUtil {
    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    private static final String WINDOWS = "win";
    private static final String LINUX = "linux";

    /**
     * 判断当前操作系统，并返回日志存储的路径
     *
     * @return 日志存储的路径
     */
    private static String whichOsLog() {
        String os = System.getProperties().getProperty("os.name");
        os = os.toUpperCase();
        if (WINDOWS.equals(os)) {
            return "D:\\tiangong\\logs\\";
        } else if (LINUX.equals(os)) {
            return "/var/tmp/logs/";
        } else {
            return "";
        }
    }

    /**
     * 判断当前操作系统，并返回图片存储的路径
     *
     * @return 图片存储的路径
     */
    private static String whichOsImage() {
        String os = System.getProperties().getProperty("os.name");

        if (LINUX.equals(os)) {
            return "/var/tmp/images/";
        } else if (os.toLowerCase().startsWith(WINDOWS)) {
            return "D:\\tiangong\\images\\";
        } else {
            return "";
        }
    }


    public static void main(String[] args) {
        deleteFile("D:\\tiangong\\images\\2018-06-19-17.12.50.518936-waste.jpg");

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            System.out.println(os + " can't gunzip");
        }

        System.out.println(whichOsImage());

        System.out.println(whichOsLog());

        System.out.println(isWindows());
    }
}
