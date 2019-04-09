package LoginTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IteratorTest {
    public static void main(String[] args) {

        Map map = new HashMap();

        map.put("1155669", "Tom");
        map.put("1155689", "Jane");
        map.put("1165669", "Kevin");
        map.put("1155660", "Gavin");

        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
    }
}
