import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente2 {

    public static void main(String[] args) {

        Socket cliente = null;
        DataInputStream entrada;
        DataOutputStream salida;
        String cadena;

        try{
            // Crear socket cliente
            cliente= new Socket("localhost",9999);

            // Obtener los InputStream y/o OutputStream del servidor
            entrada = new DataInputStream (cliente.getInputStream());
            salida = new DataOutputStream (cliente.getOutputStream());

            // Acciones
            while (true){
                String teclado;
                Scanner sc = new Scanner(System.in);
                teclado=sc.nextLine();
                salida.writeUTF(teclado);
                salida.writeUTF("Prueba 2");
                salida.writeUTF("Prueba 3");
                salida.writeUTF("adios");
                String respuesta=entrada.readUTF();
                if (respuesta.equals("salir")) {
                    break;
                }
            }
            //cadena = entrada.readUTF();


            // Cerrar los canales de entrada, salida y el socket cliente
            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
