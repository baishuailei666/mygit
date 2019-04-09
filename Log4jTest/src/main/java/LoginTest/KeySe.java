package LoginTest;

import java.util.HashMap;
import java.util.Map;

public class KeySe {
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("apple", "新鲜的苹果"); // 向列表中添加数据
        map.put("computer", "配置优良的计算机"); // 向列表中添加数据
        map.put("book", "堆积成山的图书"); // 向列表中添加数据
        String key = "book";
        boolean contains = map.containsKey(key);
        if (contains) {
            System.out.println("在Map集合中包含键名" + key);
        } else {
            System.out.println("在Map集合中不包含键名" + key);
        }

    }
}
