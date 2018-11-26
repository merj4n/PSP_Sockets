import java.io.*;
import java.net.*;
import java.util.List;

public class FicheroServidor {
    public static final int PORT = 4444;

    public static void main (String args[]) {

        DataOutputStream dos;
        ServerSocket servidor = null;
        Socket cliente;

        try{
            servidor = new ServerSocket(PORT);
            cliente = servidor.accept();
            dos = new DataOutputStream (cliente.getOutputStream());
            List<String> lista;
            ListarArchivos find = new ListarArchivos();
            lista=find.buscador("", new File("C:/Users/merjan/Desktop/file"));
            for (String elem:lista) {
                dos.writeUTF(elem);
            }

        }catch(IOException e){
            System.out.println("Error al conectar con el servidor");
        }
        }
}
