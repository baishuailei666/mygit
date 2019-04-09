package ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

public class FtpTest {

    private  FTPClient ftp;
    /**
     *
     * @param path 上传到ftp服务器哪个路径下
     * @param addr 地址
     * @param port 端口号
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    private  boolean connect(String path,String addr,int port,String username,String password) throws Exception {
        boolean result = false;
        ftp = new FTPClient();
        int reply;
        ftp.connect(addr,port);
        ftp.login(username,password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return result;
        }
        ftp.changeWorkingDirectory(path);
        result = true;
        return result;
    }
    /**
     *
     * @param file 上传的文件或文件夹
     * @throws Exception
     */
    private void upload(File file) throws Exception{
        if(file.isDirectory()){
            ftp.makeDirectory(file.getName());
            ftp.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                File file1 = new File(file.getPath()+"\\"+files[i] );
                if(file1.isDirectory()){
                    upload(file1);
                    ftp.changeToParentDirectory();
                }else{
                    File file2 = new File(file.getPath()+"\\"+files[i]);
                    FileInputStream input = new FileInputStream(file2);
                    ftp.storeFile(file2.getName(), input);
                    input.close();
                }
            }
        }else{
            File file2 = new File(file.getPath());
            FileInputStream input = new FileInputStream(file2);
            ftp.storeFile(file2.getName(), input);
            input.close();
        }
    }
    public static void main(String[] args) throws Exception{
        FtpTest t = new FtpTest();
        t.connect("/home/log/", "8.14.0.108", 21, "xlauncher", "docker@1302");
        File file = new File("d:\\logs");
        t.upload(file);
    }


//    public void test(){
//        // 创建客户端对象
//        FTPClient ftp = new FTPClient();
//        InputStream local = null;
//        try{
//            // 连接到ftp服务器
//            ftp.connect("192.168.0.2",21);
//            // 登录
//            ftp.login("ftpuser","1111");
//            // 设置上传路径
//            String path = "/home/ftpuser/image";
//            // 检查上传路径是否存在，如果不存在返回false
//            boolean flag = ftp.changeWorkingDirectory(path);
//            if (!flag) {
//                // 创建上传路径，该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
//                ftp.makeDirectory(path);
//            }
//            // 指定上传路径
//            ftp.changeWorkingDirectory(path);
//            // 指定上传文件的类型    二进制文件
//            ftp.setFileType(FTP.BINARY_FILE_TYPE);
//            // 读取本地文件
//            File file = new File("test.jpg");
//            local = new FileInputStream(file);
//            // 第一个参数是文件名
//            ftp.storeFile(file.getName(), local);
//        } catch (SocketException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 关闭文件流
//                local.close();
//                // 退出
//                ftp.logout();
//                // 断开连接
//                ftp.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
