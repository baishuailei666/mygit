package Log4jTest;


import java.net.ServerSocket;
import java.net.Socket;



public class SocketAppender {


    public static void main(String[] args) throws Exception {
        System.out.println("service is running...");
        ServerSocket socket = new ServerSocket(4560);
        while (true) {
            Socket client = socket.accept();

            Thread t = new Thread(new LogRunner(client));
            t.start();
        }
    }

}
