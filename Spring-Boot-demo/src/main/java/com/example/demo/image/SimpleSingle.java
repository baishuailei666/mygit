package com.example.demo.image;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/29 0029
 * @Desc :
 **/
public class SimpleSingle {
    private static SimpleSingle simpleSingle = new SimpleSingle();
    private SimpleSingle() {

    }
    public static SimpleSingle getInstance() {
        return simpleSingle;
    }
}
