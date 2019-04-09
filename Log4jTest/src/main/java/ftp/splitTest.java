package ftp;

public class splitTest {
    public static void main(String[] args) {
        String ip = "ftp://8.14.0.108:21/alarm-imgs/2018-06-19-17:12:40.519999-small boat.jpg";

        String a[] = ip.split("//");

        for(int i=0;i<a.length;i++){

            System.out.println(a[i]);

        }


        String ipName = ip.replaceAll("/", "=");

        System.out.println(ipName);

        String[] ipArr = ipName.split("=");

        System.out.println(ipArr[4]);

        for(int i=0;i<ipArr.length;i++){

            System.out.println(ipArr[i]);

        }
    }
}
