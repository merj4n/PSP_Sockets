import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servicioEcho = null;
        ServerSocket servicioHora = null;
        Socket socketEcho = null;
        Socket socketHora = null;
        DataInputStream entradaEcho,entradaHora;
        DataOutputStream salidaEcho,salidaHora;
        String cadena,cadena2;

        try{
            // Crear socket servidor
            servicioEcho= new ServerSocket(9998);
            servicioHora= new ServerSocket(9999);

            // Aceptar un cliente
            socketEcho= servicioEcho.accept();
            socketHora= servicioHora.accept();
            // Obtener los InputStream y/o OutputStream del servidor
            entradaEcho = new DataInputStream (socketEcho.getInputStream());
            entradaHora = new DataInputStream (socketHora.getInputStream());
            salidaEcho = new DataOutputStream (socketEcho.getOutputStream());
            salidaHora = new DataOutputStream (socketHora.getOutputStream());

            // Acciones a realizar por el servidor
            while (true) {
                cadena = entradaEcho.readUTF();
                if (cadena.equals("Bye")) {
                        salidaEcho.writeUTF("Adios");
                        salidaEcho.close();
                        entradaEcho.close();
                        socketEcho.close();
                        break;
                    } else {
                        salidaEcho.writeUTF(cadena);
                    }
            }
            while (true) {
                    cadena2 = entradaHora.readUTF();
                    if (cadena2.equals("Bye")) {
                        salidaHora.writeUTF("Adios");
                        salidaHora.close();
                        entradaHora.close();
                        socketHora.close();
                        break;
                    } else {
                        salidaHora.writeUTF(FechaHora());
                    }
            }
        } catch (IOException e) {
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
