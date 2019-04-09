package ftp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SQLTest {

    public static void main(String[] args) {
        Calendar twentyOne = Calendar.getInstance();
        twentyOne.set(Calendar.HOUR_OF_DAY, 23);
        twentyOne.set(Calendar.MINUTE, 0);
        twentyOne.set(Calendar.SECOND, 0);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                SQLTest.backup("C:\\Program Files\\MySQL\\MySQL Server 5.7", "es", "d:\\sql");
            }
        }, twentyOne.getTime(), 1000);
    }
    /**
     *
     * @param dbdir mysql数据库安装路径
     * @param dbname  数据库的名称
     * @param backdir 备份的目录
     */
    public static void backup(String dbdir, String dbname, String backdir) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        String currentTime = dateFormat.format(calendar.getTime());
        try {
            long startTime = System.currentTimeMillis();
            Runtime rt = Runtime.getRuntime();
            Process child = rt
                    .exec(dbdir + "\\bin\\mysqldump --default-character-set=utf8 -uroot -pdocker@1302 " + dbname);
            InputStream in = child.getInputStream();
            InputStreamReader xx = new InputStreamReader(in, "utf8");

            FileOutputStream fout = new FileOutputStream(new File(backdir, dbname + "_" + currentTime + ".bak"));
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            writer.write("-- Dump by Microsoul at " + dateFormat.format(calendar.getTime()) + "\r\n");

            String inStr;
            BufferedReader br = new BufferedReader(xx);
            // 这样实时写入文件很重要，网上有很多是将读取的存入字符串中，最后再写成文件，这样会导致Java的堆栈内存溢出。
            while ((inStr = br.readLine()) != null) {
                writer.write(inStr);
                writer.write("\r\n");
            }

            writer.write("\r\n-- Use " + (System.currentTimeMillis() - startTime) + "ms\r\n");

            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
        } catch (Exception e) {
            PrintStream print = null;
            try {
                print = new PrintStream(new File(backdir, currentTime + "_backup_err.log"));
                dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                currentTime = dateFormat.format(calendar.getTime());
                print.println(currentTime + "  backup failed.");
                e.printStackTrace(print);
                print.flush();
            } catch (IOException e2) {

            } finally {
                if (print != null) {
                    print.close();
                }
            }
        }

    }

}
