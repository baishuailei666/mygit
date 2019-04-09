package com.example.demo.face;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/14 0014
 * @Desc :
 **/
public class MatUtil {

    public static void main(String[] args) throws IOException {
       matUtil("D:\\sdkimage\\face-2019-03-14-09-33-58.jpg", (float) 0.262, (float) 0.0, (float) 0.506, (float) 0.9);
    }

    public static void matUtil(String fileName, float x, float y, float w, float h) throws IOException {
        File file = new File(fileName);
        BufferedImage bufferedImage = ImageIO.read(file);
        ByteArrayOutputStream buf = new ByteArrayOutputStream((int) file.length());
        ImageIO.write(bufferedImage, "jpg", buf);
        byte[] bytes = buf.toByteArray();
        // 加载库文件
        System.load("C:\\Windows\\System32\\opencv_java310.dll");

        // 将内存中byte数组转换成Mat
        Mat img = byteToMat(bytes);

        Rect rect =  new Rect((int) (x * 1920), (int) (y * 1080), 960, 890);
        //设置ROI
        Mat imgROI = new Mat(img, rect);
        // Mat类型保存图片
        Imgcodecs.imwrite("D:\\sdkimage\\rect1.jpg", imgROI);

        int ww = 960;
        int hh = 890;
        System.out.println("x-y " + x + ":" + y);
        System.out.println("w-h " + w + ":" + h);
        Rect rect1 =  new Rect((int) (ww * 0.32), (int) (hh * 0.28), 380, 490);
        System.out.println("ww+ww*w " + ww + " : " + ww*w);
        System.out.println("hh+hh*h " + hh + " : " + hh*h);
        //设置ROI
        Mat imgROI1 = new Mat(imgROI, rect1);
        // Mat类型保存图片
        Imgcodecs.imwrite("D:\\sdkimage\\rect2.jpg", imgROI1);
    }

    /**
     * Mat类型转换成byte数组
     *
     * @param matrix        要转换的Mat
     * @param fileExtension 格式为 ".jpg", ".png", etc
     * @return byte[]
     */
    private static byte[] mat2Byte(Mat matrix, String fileExtension) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, matrix, mob);
        return mob.toArray();
    }

    /**
     * byte数组转换成Mat类型
     *
     * @param image byte数组
     * @return Mat类型
     */
    private static Mat byteToMat(byte[] image) {
        BufferedImage bImage;
        Mat data = null;
        try {
            bImage = ImageIO.read(new ByteArrayInputStream(image));
            byte[] bytes = ((DataBufferByte) bImage.getRaster().getDataBuffer()).getData();
            data = new Mat(bImage.getHeight(), bImage.getWidth(), CvType.CV_8UC3);
            data.put(0, 0, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
