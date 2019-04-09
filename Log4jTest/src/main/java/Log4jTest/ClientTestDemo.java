package Log4jTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ClientTestDemo {
        //搭建服务器端
        public static void main(String[] args) throws IOException{
            ClientTestDemo socketService = new ClientTestDemo();
            //1、a)创建一个服务器端Socket，即SocketService
            socketService.oneServer();
        }
        public  void oneServer(){
            try{
                ServerSocket server=null;
                try{
                    server=new ServerSocket(10001);
                    //b)指定绑定的端口，并监听此端口。
                    System.out.println("服务器启动成功");
                    //创建一个ServerSocket在端口监听客户请求
                }catch(Exception e) {
                    System.out.println("没有启动监听："+e);
                    //出错，打印出错信息
                }
                Socket socket=null;
                try{
                    socket=server.accept();
                    //2、调用accept()方法开始监听，等待客户端的连接
                    //使用accept()阻塞等待客户请求，有客户
                    //请求到来则产生一个Socket对象，并继续执行
                }catch(Exception e) {
                    System.out.println("Error." + e);
                    //出错，打印出错信息
                }
                //3、获取输入流，并读取客户端信息
                String line;
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //由Socket对象得到输入流，并构造相应的BufferedReader对象
                PrintWriter writer=new PrintWriter(socket.getOutputStream());
                //由Socket对象得到输出流，并构造PrintWriter对象
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                //由系统标准输入设备构造BufferedReader对象
                System.out.println("Client*:" + in.readLine());
                System.out.println("socket.getInputStream()*:" + socket.getInputStream());



//                OutputStream os = new FileOutputStream("D:\\dim1.jpg");
//                os.write(in.readLine().substring(35).getBytes());

                System.out.println("***:" + Arrays.toString(ImgUtil.readFromInputStream(socket.getInputStream())));
                System.out.println("image:" + ImgUtil.getBlobViaInputStream(ImgUtil.readFromInputStream(socket.getInputStream())));

                File file = new File("D:\\dim.jpg");
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(ImgUtil.readFromInputStream(socket.getInputStream()));

                //在标准输出上打印从客户端读入的字符串
                line=br.readLine();
                //从标准输入读入一字符串
                //4、获取输出流，响应客户端的请求
                while(!line.equals("end")){
                    //如果该字符串为 "bye"，则停止循环
                    writer.println(line);
                    //向客户端输出该字符串
                    writer.flush();

                    //刷新输出流，使Client马上收到该字符串
                    System.out.println("Server:"+line);
                    //在系统标准输出上打印读入的字符串
                    System.out.println("Client:"+in.readLine());
                    //从Client读入一字符串，并打印到标准输出上
                    line=br.readLine();
                    //从系统标准输入读入一字符串
                } //继续循环

                //5、关闭资源
                writer.close(); //关闭Socket输出流
                in.close(); //关闭Socket输入流
                socket.close(); //关闭Socket
                server.close(); //关闭ServerSocket
            }catch(Exception e) {
                //出错，打印出错信息
                System.out.println("Error : "+e);
            }
        }
    }