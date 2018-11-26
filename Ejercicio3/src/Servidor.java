import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket ss;
        try {
            ss = new ServerSocket(9998);
            int id=0;
            while (true){
                Socket sk;
                sk = ss.accept();
                new ServerHilo(sk,id).start();
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
