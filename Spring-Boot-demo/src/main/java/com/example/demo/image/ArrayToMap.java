package com.example.demo.image;


import org.apache.commons.lang.ArrayUtils;
import java.util.Map;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/29 0029
 * @Desc :
 **/
public class ArrayToMap {
    public static void main(String[] args) {
        String[][] countries = {
                {"United States", "New York"},
                {"United Kingdom", "London"},
                {"Nether land", "Amsterdam"},
                {"Japan", "Tokyo"},
                {"France", "Paris"}
        };

        Map map = ArrayUtils.toMap(countries);
        System.out.println("Map:" + map);
        System.out.println("Capital of France is:" + map.get("France"));
    }
}
