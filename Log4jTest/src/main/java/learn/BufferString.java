package learn;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/9/20 0020
 * @Desc :
 **/
public class BufferString {
    public static void main(String[] args) {
        String string = "";
        long startTime = System.nanoTime();
        for (int i = 20000; i < 50000; ++i)
            string += (char) i;
        long endTime = System.nanoTime();
        System.out
                .println("String耗时：" + (endTime - startTime) / 1000000 + "ms");
        StringBuffer stringBuffer = new StringBuffer();
        startTime = System.nanoTime();
        for (int i = 20000; i < 50000; ++i)
            stringBuffer.append((char) i);
        endTime = System.nanoTime();
        System.out.println("StringBuffer耗时：" + (endTime - startTime) / 1000000
                + "ms");
        StringBuilder stringBuilder = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 20000; i < 50000; ++i)
            stringBuilder.append((char) i);
        endTime = System.nanoTime();
        System.out.println("StringBuilder耗时：" + (endTime - startTime) / 1000000
                + "ms");
    }
}
