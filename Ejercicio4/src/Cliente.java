import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class PersonaEcho extends Thread {

    private int id;
    private Socket sk;
    private DataInputStream dis;
    private DataOutputStream dos;

    public PersonaEcho(int id) {
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
                    desconexion();
                }else {
                    System.out.println(contesta);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        desconexion();
    }
    public void desconexion(){
        try {
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class PersonaTime extends Thread {
    private int id;
    private Socket sk;
    private DataInputStream dis;
    private DataOutputStream dos;

    public PersonaTime(int id){
        this.id=id;
    }
    @Override
    public void run(){

        try {
            sk = new Socket("localhost",9999);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            dos.writeUTF("Hora?");
            System.out.println("Cliente[" + this.id + "] :"+ "Hora?");
            while (true){
                String contesta = dis.readUTF();
                if (contesta.equals("Bye")){
                    System.out.println(contesta);
                    desconexion();
                }else {
                    System.out.println(contesta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        desconexion();
    }
    public void desconexion(){
        try {
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Cliente{
    public static void main(String[] args) {
        List<Thread> clientesEcho = new ArrayList<>();
        List<Thread> clientesTime = new ArrayList<>();
        for (int i=0; i<3;i++){
            clientesEcho.add(new PersonaEcho(i));
            clientesTime.add(new PersonaTime(i));
        }
        for (Thread thread:clientesEcho){
            thread.start();
        }
        for (Thread thread:clientesTime){
            thread.start();
        }
    }
}