package UUID;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 统计UUID的长度变化范围
 */
public class UUIDTestA {
    private static final String fileName = "D:/Launcher/logs/UUIDout.txt";

    private static final String[] chars = { // <br>
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", // <br>
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", // <br>
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", // <br>
            "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", // <br>
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", // <br>
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", // <br>
            "Y", "Z", "a1", "a2" // <br>
    };

    // 统计UUID的长度变化范围
    public static void main(String[] args) throws InterruptedException {
        int time = 1 * 10000 * 1;
        for (int j = 0; j < 15; j++) {
            Map<Integer, Integer> sum = new HashMap<>();
            Map<String, Integer> sumStr = new HashMap<>();
            for (int ii = 0; ii < time; ii++) {
                String uuid = uuid(j);
                int sbLength = uuid.length();
                String sb = uuid.substring(0, 1);
                if (sum.get(Integer.valueOf(sbLength)) != null) {
                    int v = sum.get(Integer.valueOf(sbLength)).intValue();
                    v++;
                    sum.put(Integer.valueOf(sbLength), v);
                } else {
                    sum.put(Integer.valueOf(sbLength), 1);
                }
                if (sumStr.get(sb) != null) {
                    int v = sumStr.get(sb).intValue();
                    v++;
                    sumStr.put(sb, v);
                } else {
                    sumStr.put(sb, 1);
                }
            }
            write("j：" + j);
            for (Integer in : sum.keySet()) {
                write(in + "：" + sum.get(in));
                // System.out.println(in + "：" + sum.get(in));
            }
        }
    }

    /**
     * @description: 生成UUID<br/>
     *               可以确定开头字母来区别业务<br/>
     *               num16：必须是0--15以内的数字<br/>
     *              @param：num16：<br/>
     *               00-->>"0", "1", "2", "3",<br/>
     *               01-->>"4", "5", "6", "7",<br/>
     *               02-->>"8", "9", "a", "b",<br/>
     *               03-->>"c", "d", "e", "f",<br/>
     *               04-->>"g", "h", "i", "j",<br/>
     *               05-->>"k", "l", "m", "n",<br/>
     *               06-->>"o", "p", "q", "r",<br/>
     *               07-->>"s", "t", "u", "v",<br/>
     *               08-->>"w", "x", "y", "z",<br/>
     *               09-->>"A", "B", "C", "D",<br/>
     *               10-->>"E", "F", "G", "H",<br/>
     *               11-->>"I", "J", "K", "L",<br/>
     *               12-->>"M", "N", "O", "P",<br/>
     *               13-->>"Q", "R", "S", "T",<br/>
     *               14-->>"U", "V", "W", "X",<br/>
     */
    public static String uuid(int num16) {
        if (num16 < 0 || num16 > 15) {
            num16 = 0;
        }
        StringBuilder sb = new StringBuilder();
        String pre = Integer.toHexString(num16);
        String uid = pre + UUID.randomUUID().toString().replaceAll("-", "");
        int length = uid.length();
        // 将16进制数转换成64进制数
        // 2的4次方转化成2的6次方的数据
        // bcb da5 f41 172 4bc 28b 920 b1c 5f4 422 6e
        // 3位转2位
        int i = 0;
        for (; i < length; i = i + 3) {
            // 16进制数转化成10进制数
            int end = i + 3;
            if (end >= length) {
                end = length;
            }
            int t = Integer.parseInt(uid.substring(i, end), 16);
            // 10进制数转化成64进制数
            sb.append(chars[t / 64]);// 第一个数据
            sb.append(chars[t % 64]);// 第二个数据
        }
        // write(sb.toString().substring(0, 1) + "----" + sb.toString());
        write(sb);
        // System.out.println(sb);
        return sb.toString();
    }

    public static void write(Object out) {
        if (out == null) {
            return;
        }
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(out.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
