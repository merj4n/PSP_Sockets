import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ejercicio2Cliente {
    public static void main(String[] args) {

        Socket cliente = null;
        Socket cliente2 = null;
        DataInputStream entrada,entrada2;
        DataOutputStream salida,salida2;
        String cadena;

        try{
            // Crear socket cliente
            cliente= new Socket("localhost",9999);
            cliente2 = new Socket("localhost",9998);

            // Obtener los InputStream y/o OutputStream del servidor
            entrada = new DataInputStream (cliente.getInputStream());
            entrada2 = new DataInputStream (cliente2.getInputStream());
            salida = new DataOutputStream (cliente.getOutputStream());
            salida2 = new DataOutputStream (cliente2.getOutputStream());

            // Acciones
            salida.writeUTF("Prueba 1");
            salida.writeUTF("Prueba 2");
            salida.writeUTF("Bye");
            salida2.writeUTF("Prueba 3");
            salida2.writeUTF("Bye");
            while (true) {
                String respuesta = entrada.readUTF();
                String respuesta2 = entrada2.readUTF();
                System.out.println(respuesta);
                System.out.println(respuesta2);
                if (respuesta.equals("Bye")||respuesta2.equals("Bye")){
                    break;
                }
            }
            // Cerrar los canales de entrada, salida y el socket cliente
            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
