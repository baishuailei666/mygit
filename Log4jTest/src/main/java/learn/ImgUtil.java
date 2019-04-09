package learn;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片工具类，图片水印、文字水印、缩放、补白等
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/18 0018
 * @Desc :
 **/
public class ImgUtil {

    /**
     * 图片格式
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImgUtil() {}

    /**
     * 添加图片水印
     * @param targetImg 目标图片路径
     * @param waterImg 水印图片路径
     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度（0.0 -- 1.0, 0.0完全透明， 1.0完全不透明）
     */
    public final static void pressImage(String targetImg, String waterImg, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);

            // 水印文件
            Image waterImage = ImageIO.read(new File(waterImg));
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            // 水印文件结束
            g.drawImage(waterImage, x, y, width_1, height_1, null);
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加文字水印
     * @param targetImg 目标图片路径
     * @param pressText 水印文字
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色，   如：Color.GREEN
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg,String pressText,String fontName,int fontStyle,int fontSize,Color color,int x,int y,float alpha){
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image,0,0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_wi = fontSize*getTextLength(pressText);
            int height_wi = fontSize;

            int widthDiff = width-width_wi;
            int heightDiff = height-height_wi;
            if(x<0){
                x = widthDiff/2;
            }else if(x>widthDiff){
                x=widthDiff;
            }

            if(y<0){
                y = heightDiff/2;
            }else if(y>heightDiff){
                y = heightDiff;
            }
            g.drawString(pressText, x, y+height_wi);//水印文件
            g.dispose();
            ImageIO.write(bufferedImage, "JPEG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算文字像素长度
     * @param text
     * @return
     */
    private static int getTextLength(String text){
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            int wordLength = String.valueOf(text.charAt(i)).getBytes().length;
            if(wordLength > 1){
                length+=(wordLength-1);
            }
        }

        return length%2==0 ? length/2:length/2+1;
    }


    /**
     * 旋转任意度数的方法
     * @param targetImg 目标图片路径
     * @param degree 旋转度数
     * @param bgcolor 背景颜色
     * @throws IOException
     */
    public static void rotateImage(String targetImg, int degree, Color bgcolor) throws IOException {

        File file = new File(targetImg);
        BufferedImage sourceImage = ImageIO.read(file);
        int iw = sourceImage.getWidth();//原始图象的宽度
        int ih = sourceImage.getHeight();//原始图象的高度
        int w = 0;
        int h = 0;
        int x = 0;
        int y = 0;
        degree = degree % 360;
        if (degree < 0)
            degree = 360 + degree;//将角度转换到0-360度之间
        double ang = Math.toRadians(degree);//将角度转为弧度

        /**
         *确定旋转后的图象的高度和宽度
         */

        if (degree == 180 || degree == 0 || degree == 360) {
            w = iw;
            h = ih;
        } else if (degree == 90 || degree == 270) {
            w = ih;
            h = iw;
        } else {
            int d = iw + ih;
            w = (int) (d * Math.abs(Math.cos(ang)));
            h = (int) (d * Math.abs(Math.sin(ang)));
        }

        x = (w / 2) - (iw / 2);//确定原点坐标
        y = (h / 2) - (ih / 2);
        BufferedImage rotatedImage = new BufferedImage(w, h, sourceImage.getType());
        Graphics2D gs = (Graphics2D)rotatedImage.getGraphics();
        if(bgcolor==null){
            rotatedImage  = gs.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        }else{
            gs.setColor(bgcolor);
            gs.fillRect(0, 0, w, h);//以给定颜色绘制旋转后图片的背景
        }

        //有两种旋转使用方式，第一使用AffineTransformOp，第二使用Graphics2D
	   /*
	    * AffineTransform at = new AffineTransform();
	    at.rotate(ang, w / 2, h / 2);//旋转图象
	    at.translate(x, y);
	    AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
	    op.filter(sourceImage, rotatedImage);
	    sourceImage = rotatedImage;

	    ImageIO.write(sourceImage, "PNG", file);//这里的格式化请使用PNG格式，否则旋转后会出现红眼效果
*/

        BufferedImage bufferedImage = new BufferedImage(w, h, sourceImage.getType());
        Graphics2D g = bufferedImage.createGraphics();
        if(bgcolor==null){
            g.setColor(Color.WHITE);
        }else{
            g.setColor(bgcolor);
        }
        g.fillRect(0, 0, w, h);//以给定颜色绘制旋转后图片的背景

        g.rotate(Math.toRadians(degree), w/2, h/2);
        g.translate(x, y);
        g.drawImage(sourceImage, 0, 0, null);
        g.dispose();

        ImageIO.write(bufferedImage, "JPEG", file);//这里的JPEG也可以是PNG
    }


    public static void main(String[] args) {
//        pressImage("C:\\Users\\Administrator\\Pictures\\beautiful\\s1.jpg", "C:\\Users\\Administrator\\Pictures\\beautiful\\what.png", 5,5, (float) 0.5);
//        pressText("C:\\Users\\Administrator\\Pictures\\beautiful\\s1.jpg", "白帅雷s", "楷体", Font.BOLD, 50, Color.GREEN,5,5, (float) 0.5);

    }
}
