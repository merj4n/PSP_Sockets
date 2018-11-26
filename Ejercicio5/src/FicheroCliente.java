import java.io.*;
import java.net.*;

public class FicheroCliente {
    public static void main(String[] args) {
        DataInputStream dis;
        Socket socketCliente = null;
        String cadena;
        int i=0;

        try{
            socketCliente = new Socket("localhost", 4444);
            dis = new DataInputStream (socketCliente.getInputStream());
            while (true){
                cadena = dis.readUTF();
                System.out.println(i+" "+cadena);
                i++;
                if(cadena.isEmpty()) {
                    socketCliente.close();
                    dis.close();
                }
            }
        }catch(IOException e){
            System.err.println("No puede establecer conexion");
        }
    }
}
