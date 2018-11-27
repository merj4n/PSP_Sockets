import java.io.*;
import java.net.*;
import java.util.List;

public class FicheroServidor {
    public static final int PORT = 4444;

    public static void main (String args[]) {

        DataOutputStream dos;
        DataInputStream dis;
        ServerSocket servidor = null;
        Socket cliente;
        boolean bucle=true;
        String cadena="";
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] byteArray;
        int in;
        String file;

        try{
            servidor = new ServerSocket(PORT);
            cliente = servidor.accept();
            dos = new DataOutputStream(cliente.getOutputStream());
            dis = new DataInputStream(cliente.getInputStream());
            while (bucle) {
                List<String> lista;
                ListarArchivos find = new ListarArchivos();
                lista = find.buscador("", new File("C:/Users/merjan/Desktop/file"));
                String total=String.valueOf(lista.size());
                dos.writeUTF(total);
                for (String elem : lista) {
                    dos.writeUTF(elem);
                }
                while (true) {
                    //Obtengo el numero de fichero a enviar
                    cadena = dis.readUTF();
                    //Si es -1 salimos del bucle
                    if (cadena.equals("-1")) {
                        break;
                    }
                    else{
                        //Recibimos el nombre del fichero
                        file = lista.get(Integer.parseInt(cadena));
                        //Envio nombre fichero al cliente
                        dos.writeUTF(file);
                        //Defino ruta del fichero
                        final File localFile = new File( "C:/Users/merjan/Desktop/file/"+file );
                        //Defino el buffer de entrada-salida del fichero
                        bis = new BufferedInputStream(new FileInputStream(localFile));
                        bos = new BufferedOutputStream(cliente.getOutputStream());
                        //Enviamos el fichero
                        byteArray = new byte[8192];
                        while ((in = bis.read(byteArray)) != -1){
                            bos.write(byteArray,0,in);
                        }
                    }
                    bos.close();
                    bis.close();
                    dis.close();
                    bucle=false;
                }
            cliente.close();
            servidor.close();
            dos.close();
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
