import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHiloEcho extends Thread{
    private Socket s;
    private int id;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ServerHiloEcho(Socket s, int id){
        this.s=s;
        this.id=id;
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    public void desconexion(){
        try {
            dis.close();
            dos.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        String cadena;
        try {
            while (true){
                cadena = dis.readUTF();
                if (cadena.equals("Bye")) {
                    dos.writeUTF("Servidor[" + this.id + "] :"+"Bye");
                    desconexion();
                } else {
                    dos.writeUTF("Servidor[" + this.id + "] :"+cadena);
                    System.out.println(cadena + "[" + this.id + "]");
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        desconexion();
    }
}
