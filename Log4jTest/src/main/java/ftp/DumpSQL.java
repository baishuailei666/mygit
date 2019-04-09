package ftp;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 导出sql文件
 */
public class DumpSQL extends TimerTask {

    public void run() {
        DumpSQL.backup();
    }

    public static void main(String[] args) {
        DumpSQL.backup();
    }

    public static void backup() {
        try {
            Runtime rt = Runtime.getRuntime();

            Process child = rt.exec("C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump -uroot -pdocker@1302 tiangong_cms cms_alert_log");
            InputStream in = child.getInputStream();
            System.out.println(child);
            InputStreamReader xx = new InputStreamReader(in, "utf8");
            String inStr;
            StringBuilder sb = new StringBuilder("");
            String outStr;
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr).append("\r\n");
            }
            outStr = sb.toString();

            File file = new File("d:\\sql\\dumpTest.sql");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileOutputStream fout = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            writer.flush();

            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

//    //数据库脚本
//        public static void main(String[] args) {
//
//            String filepath = "d:\\test.sql"; // 备份的路径地址
//            //新建数据库test
//
//            String stmt1 = "mysqladmin -u root -proot create test";
//
//            String stmt2 = "mysql -u root -proot test < " + filepath;
//            String[] cmd = {"cmd", "/c", stmt2};
//
//            try {
//                Runtime.getRuntime().exec(stmt1);
//                Runtime.getRuntime().exec(cmd);
//                System.out.println("数据已从 " + filepath + " 导入到数据库中");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }



//TestTimer.java (定时器)

//    class TestTimer {
//        public static void main(String[] args) throws ParseException {
//
//            TestTimer tt = new TestTimer();
//
//            tt.vick();
//
//        }
//
//        public void vick() throws ParseException {
//
//            Timer timer = new Timer();
//
//            DumpSQL timerTask = new DumpSQL();
//
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            Date d = df.parse("2010-08-10 16:43:00");   //在这里设置开始时间
//
//            long delay = d.getTime() - System.currentTimeMillis();   //延迟多少毫秒后开始调用
//
//            long cycle = 100000;   //循环调用的时间间隔
//
//            timer.schedule(timerTask, delay, cycle);
//
//        }
//
//    }

