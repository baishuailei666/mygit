package ftp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDemo {
    private BufferedReader in_br;
    private RandomAccessFile out_r;
    private File file = new File("d:\\2018FileDemo.txt");

    {    //初始化 输出流
        in_br = new BufferedReader(new InputStreamReader(System.in));
        try {
            out_r = new RandomAccessFile(file, "rw");
            // 设置从该文件末尾开始写入
            out_r.seek(out_r.length());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取控制台数据
    public void read() {
        System.out.println("请输入数据：");
        String str = null;
        try {
            while ((str = in_br.readLine()) != null) {
                splitFile(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out_r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //拆分数据循环创建50字节大小的文件
    public void splitFile(String str) throws IOException{
        // 解决乱码问题
        byte[] byte2 = str.getBytes();
        long len = str.length();
        /*
         * 如果输入的数据与目标文件的大小和大于50，且目标文件大小大于50 就创建新文件
         * 若目标文件大小小于50，就写入50-out_r.length()长度的数据
         */
        if (len+out_r.length() >=50) {
            if(out_r.length()>=50){
                //创建文件方法
                createFile(file);
                splitFile(str);
            }else{
                long buff = 50-out_r.length();
                out_r.seek(out_r.length());
                out_r.write(byte2,0,(int)buff);
                if((len-buff)>0){
                    str = str.substring((int)buff);
                    //用递归
                    splitFile(str);
                }
            }
        }else{
            out_r.write(byte2);
            return;
        }
    }

    // 创建新文件以当前时间命名，重新赋值输出流
    public void createFile(File file) throws IOException {
        // 获取当前时间
        Date date = new Date();
        // 定义文件名格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 把当前时间以定义的格式 格式化
        String formatDate = sdf.format(date);
        String str1 = file.getParent() + "\\";
        String str2 = str1.concat(formatDate);
        // 定义路径
        String nameDate = str2 + ".log";
        // 获得写入目标文件
        out_r = new RandomAccessFile(nameDate, "rw");
    }

    public static void main(String[] args) throws ParseException, IOException {
        FileDemo wf = new FileDemo();
        wf.read();
    }
}
