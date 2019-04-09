package com.example.demo.image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/29 0029
 * @Desc :
 **/
public class ImageUtil {

    /**
     * 图片转换成二进制数组
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] imageToByte(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

    /**
     * 图片转换成二进制数组
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] imageToByte1(String path) throws IOException {
        File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 图片转换成二进制数组
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] imageToByte2(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != 0) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 二进制数组转换成图片
     *
     * @param bytes
     * @param path
     * @throws IOException
     */
    public static void byteToImage(byte[] bytes, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    /**
     * 二进制数组转换成图片
     *
     * @param bytes
     * @param path
     * @throws IOException
     */
    public static void byteToImage1(byte[] bytes, String path) throws IOException {
        FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(new File(path));
        fileImageOutputStream.write(bytes, 0, bytes.length);
        fileImageOutputStream.close();
    }

}
