package classic;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FTP服务器工具类
 * @author 白帅雷
 * @date 2018-06-01
 */
public class FtpUtil {

    private static FTPClient ftp;

    //ftp服务器地址
    public static String hostName = "8.14.0.108";
    //ftp服务器端口号默认为21
    public static Integer port = 21;
    //ftp登录账号
    public static String userName = "xlauncher";
    //ftp登录密码
    public static String password = "docker@1302";

    private static String path = "alarm-imgs";

    public FtpUtil() {}

    public static String[] imgPaths = {"ftp://8.14.0.108:21/alarm-imgs/2018-08-30-17:12:10.095993-fishman.jpg",
                                "ftp://8.14.0.108:21/alarm-imgs/2018-08-30-17:09:15.763289-fishman.jpg",
                                "ftp://8.14.0.108:21/alarm-imgs/2018-08-30-17:08:16.387586-fishman.jpg",
                                "ftp://8.14.0.108:21/alarm-imgs/2018-08-30-17:03:07.452414-polluter.jpg",
                            "ftp://8.14.0.108:21/alarm-imgs/2018-08-30-17:02:42.091738-fishman.jpg"};

    public static void main(String[] args) {
        String baseFileName = "test";
        int j = 0;
        for (int i = 0; i < 1; i++) {
            for (String img :imgPaths) {
                String fileName = baseFileName + "\\" + j + ".jpg";
                getImgFromFtpServer(img, fileName);
//                System.out.println(getImgFromFtpServer(img, fileName).length);
                ++j;
            }
        }
    }

    /**
     * 从Ftp服务器获取图片资源
     * @date 2018-07-24 zxl
     * @param sourcepath Ftp服务器资源路径，例如：ftp://8.14.0.108:21/alarm-imgs/2018-07-23-13:42:07.736601-person.jpg
     */
    public static byte[] getImgFromFtpServer(String sourcepath, String fileName) {
        String patternStart = "/";
        Pattern pattern = Pattern.compile(patternStart);
        Matcher matcher = pattern.matcher(sourcepath);
        List<Integer> indexList = new ArrayList<>();
        while (matcher.find()) {
            indexList.add(matcher.start());
        }
        //ftp远端路径
        String remoteDirectory = sourcepath.substring(indexList.get(2), indexList.get(indexList.size() - 1) + 1);
//        文件路径
        String remoteFileName = sourcepath.substring(indexList.get(indexList.size() - 1) + 1);
        System.out.println("从Ftp服务器获取图片资源remoteDirectory: " + remoteDirectory);
        System.out.println("从Ftp服务器获取图片资源remoteFileName: " + remoteFileName);
        String path = remoteDirectory + remoteFileName;
        System.out.println("path:" + path);
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            //链接ftp服务器
            ftpClient.connect(hostName, port);
            //登录ftp服务器
            ftpClient.login(userName, password);
            //设置编码格式
            ftpClient.setAutodetectUTF8(true);
            //设置以二进制传送
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);

            //设置linux ftp服务器
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            ftpClient.configure(conf);

            if (!ftpClient.isConnected()) {
                System.out.println("connect failed...ftp服务器:" + hostName + ":" + port);
                return null;
            } else {
                System.out.println("connect success...ftp服务器:" + hostName + ":" + port);
            }
            //设置被动访问模式
            ftpClient.setRemoteVerificationEnabled(false);
            ftpClient.enterLocalPassiveMode();
            //更改远程工作目录
            //ftpClient.changeWorkingDirectory(remoteDirectory);
            File file = new File("./"+fileName);
            OutputStream  output = new FileOutputStream(file);
            System.out.println(ftpClient.retrieveFile(sourcepath, output));
            output.close();

            InputStream is = ftpClient.retrieveFileStream(path);
            System.out.println("---->" + is.available());
            //将从服务器上拉取的图片字节流存入本地文件

//            File file = new File("d:\\"+fileName);
//            FileOutputStream fo = new FileOutputStream(file);
//            byte[] cache = new byte[32];
//            int len;
//            while (is != null && (len = is.read(cache)) != -1) {
//                fo.write(cache, 0, len);
//            }
//            fo.flush();
//            fo.close();

//            int count;
//            byte[] bytes = new byte[1024 * 24];
//            if (is == null) {
//                logger.warn("服务器上指定的图片资源不存在.资源路径：" + sourcepath);
//                return null;
//            }
//            while ((count = is.read(bytes)) != -1) {
//                System.out.println("byteArrayOutputStream.write count:" + count);
//                byteArrayOutputStream.write(bytes, 0, count);
//            }
//            System.out.println("count:" + count);
//            byte[] result = byteArrayOutputStream.toByteArray();
            //ftpClient.noop();
            ftpClient.logout();
            ftpClient.disconnect();
//            return result;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    /**
     *  连接ftp服务器
     *
     * @param path 上传到ftp服务器哪个路径下，值为空代表根目录
     * @param ip ftp服务器地址
     * @param port ftp服务器端口号，默认21
     * @param username ftp服务器用户名
     * @param password ftp服务器密码
     * @return boolean
     */
    public static boolean connect(String path, String ip, int port, String username, String password) {
        ftp = new FTPClient();
        int reply;
        try {
            ftp.connect(ip,port);
            ftp.login(username,password);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            ftp.changeWorkingDirectory(path);
        } catch (ConnectException e) {
            System.out.println("拒绝连接-" + e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 上传文件
     *
     * @param file 上传的文件或文件夹
     * @return 上传成功返回1，失败返回0
     */
    public int upload(File file) {
        if(file.isDirectory()) {
            try {
                ftp.makeDirectory(file.getName());
                ftp.changeWorkingDirectory(file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] files = file.list();
            if (files != null) {
                for (String file3 : files) {
                    File file1 = new File(file.getPath() + "\\" + file3);
                    if (file1.isDirectory()) {
                        upload(file1);
                        try {
                            ftp.changeToParentDirectory();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            File file2 = new File(file.getPath() + "\\" + file3);
                            FileInputStream input = new FileInputStream(file2);
                            ftp.storeFile(file2.getName(), input);
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return 1;
            }
        } else {
            try {
                if (createDirectory(path)) {
                    ftp.changeWorkingDirectory(path);
                    FileInputStream input = new FileInputStream(new File(file.getPath()));
                    ftp.storeFile(file.getName(), input);
                    input.close();
                    return 1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 创建多级目录
     *
     * @param remote ftp服务器路径
     * @return 布尔值
     * @throws IOException IOException
     */
    public boolean createDirectory(String remote) throws IOException {
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        String slash = "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase(slash)
                && !ftp.changeWorkingDirectory(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith(slash)) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = remote.substring(start, end);
                if (!ftp.changeWorkingDirectory(subDirectory)) {
                    if (ftp.makeDirectory(subDirectory)) {
                        ftp.changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录失败！");
                        return false;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return true;
    }

}
