import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ejercicio2Servidor {
    public static void main(String[] args) {
        ServerSocket servicio = null;
        ServerSocket servicio2 = null;
        Socket socketServicio = null;
        Socket socketServicio2 = null;
        DataInputStream entrada,entrada2;
        DataOutputStream salida,salida2;
        String cadena,cadena2;

        try{
            // Crear socket servidor
            servicio= new ServerSocket(9998);
            servicio2= new ServerSocket(9999);

            // Aceptar un cliente
            socketServicio= servicio.accept();
            socketServicio2= servicio2.accept();

            // Obtener los InputStream y/o OutputStream del servidor
            entrada = new DataInputStream (socketServicio.getInputStream());
            entrada2 = new DataInputStream (socketServicio2.getInputStream());
            salida = new DataOutputStream (socketServicio.getOutputStream());
            salida2 = new DataOutputStream (socketServicio2.getOutputStream());

            // Acciones a realizar por el servidor
            while (true) {
                cadena = entrada.readUTF();
                cadena2 =entrada2.readUTF();
                if(socketServicio.isConnected()) {
                    if (cadena.equals("Bye")) {
                        salida.writeUTF("Puerto 9999");
                        break;
                    } else {
                        salida.writeUTF(cadena);
                    }
                }if (socketServicio2.isConnected()) {
                    if (cadena2.equals("Bye")) {
                        salida2.writeUTF("Puerto 9998");
                        break;
                    } else {
                        salida2.writeUTF(FechaHora());
                    }
                }
            }

            // Cerrar los canales de entrada, salida, el socket cliente y el serversocket
            salida.close();
            salida2.close();
            entrada.close();
            entrada2.close();
            socketServicio.close();
            socketServicio2.close();
            servicio.close();
            servicio2.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static String FechaHora() {

            Date date = new Date();
//Caso 1: obtener la hora y salida por pantalla con formato:
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            String hora="Hora: "+hourFormat.format(date);
            return hora;
        }

}
