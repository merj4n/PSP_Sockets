import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteEcho {
    public static void main(String[] args) {

        Socket cliente = null;
        DataInputStream entrada;
        DataOutputStream salida;
        String cadena;

        try{
            // Crear socket cliente
            cliente= new Socket("localhost",9998);

            // Obtener los InputStream y/o OutputStream del servidor
            entrada = new DataInputStream (cliente.getInputStream());
            salida = new DataOutputStream (cliente.getOutputStream());

            // Acciones
            salida.writeUTF("Prueba 1");
            salida.writeUTF("Prueba 2");
            salida.writeUTF("Bye");
            while (true) {
                cadena = entrada.readUTF();
                if (cadena.equals("Bye")){
                    break;
                }else {
                    System.out.println(cadena);
                }
            }
            // Cerrar los canales de entrada, salida y el socket cliente
            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
        }
    }
}
