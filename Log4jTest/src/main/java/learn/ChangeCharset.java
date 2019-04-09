package learn;

import java.io.UnsupportedEncodingException;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class ChangeCharset {
    public static final String ISO_8859_1 = "ISO-8859-1";

    public static final String UTF_8 = "UTF-8";

    public static final String GBK = "GBK";

    public static final String GB2312 = "GB2312";

    public String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, ISO_8859_1);
    }

    public String toUTF_8(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, UTF_8);
    }

    public String toGBK(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, GBK);
    }

    public String toGB2312(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, GB2312);
    }

    public String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);
        }
        return null;
    }

    public String changeCharset(String str, String oldCharset, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        ChangeCharset test = new ChangeCharset();
        String str = "This is a 中文的 string";
        System.out.println("str:" + str);
        String iso8859_1 = test.toISO_8859_1(str);
        System.out.println("转换为ISO-8859-1：" + iso8859_1);
        String utf_8 = test.toUTF_8(str);
        System.out.println("转换为UTF-8：" + utf_8);
        String gbk = test.toGBK(str);
        System.out.println("转换为GBK码：" + gbk);
        String gb2312 = test.toGB2312(str);
        System.out.println("转换为GB2312码：" + gb2312);
    }
}
