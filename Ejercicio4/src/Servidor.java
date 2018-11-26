import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

    public class Servidor {
        public static void main(String[] args) {
            ServerSocket ssEcho,ssTime;
            try {
                ssEcho = new ServerSocket(9998);
                ssTime = new ServerSocket(9999);
                int id=0;
                while (true){
                    Socket ske;
                    Socket skt;
                    ske = ssEcho.accept();
                    skt = ssTime.accept();
                    new ServerHiloEcho(ske,id).start();
                    new ServerHiloTime(skt,id).start();
                    id++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

