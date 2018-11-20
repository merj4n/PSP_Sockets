import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio1Servidor {
    public static void main(String[] args) {

        ServerSocket servicio = null;
        Socket socketServicio = null;
        DataInputStream entrada;
        DataOutputStream salida;
        String cadena;

        try{
            // Crear socket servidor
            servicio= new ServerSocket(9999);

            // Aceptar un cliente
            socketServicio= servicio.accept();

            // Obtener los InputStream y/o OutputStream del servidor
            entrada = new DataInputStream (socketServicio.getInputStream());
            salida = new DataOutputStream (socketServicio.getOutputStream());

            // Acciones a realizar por el servidor
            while (true) {
                cadena = entrada.readUTF();
                if (cadena.equals("Bye")) {
                    salida.writeUTF(cadena);
                    break;
                }else{
                    salida.writeUTF(cadena);
                }
            }

            // Cerrar los canales de entrada, salida, el socket cliente y el serversocket
            salida.close();
            entrada.close();
            socketServicio.close();
            servicio.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
