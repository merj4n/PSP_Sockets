import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Persona extends Thread {

    private int id;
    private Socket sk;
    private DataInputStream dis;
    private DataOutputStream dos;

    public Persona(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        try {
            sk = new Socket("localhost", 9998);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            dos.writeUTF("Hola");
            System.out.println("Cliente[" + this.id + "] :"+ "Hola");
            dos.writeUTF("Que tal?");
            System.out.println("Cliente[" + this.id + "] :" + "Que tal?");
            dos.writeUTF("Bye");
            System.out.println("Cliente[" + this.id + "] :" + "Bye");

            while (true) {
                String contesta = dis.readUTF();
                if (contesta.equals("Bye")){
                    System.out.println(contesta);
                    dis.close();
                    dos.close();
                    sk.close();
                }else {
                    System.out.println(contesta);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
public class Cliente{
    public static void main(String[] args) {
        List<Thread> clientes = new ArrayList<>();
        for (int i=0; i<5;i++){
            clientes.add(new Persona(i));
        }
        for (Thread thread:clientes){
            thread.start();
        }
    }
}
