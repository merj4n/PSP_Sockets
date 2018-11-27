import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FicheroCliente {
    public static void main(String[] args) {
        DataInputStream dis;
        DataOutputStream dos;
        Socket socketCliente = null;
        String cadena="-1";
        String opcion="";
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] receivedData;
        int i=0;
        int total;

        try{
            socketCliente = new Socket("localhost", 4444);
            dis = new DataInputStream (socketCliente.getInputStream());
            dos = new DataOutputStream(socketCliente.getOutputStream());
            cadena =dis.readUTF();
            total=Integer.parseInt(cadena);
            while (i!=total){
                cadena = dis.readUTF();
                System.out.println(i+" "+cadena);
                i++;

            }
            System.out.println("Elige un numero de fichero: ");
            Scanner sc = new Scanner(System.in);
            //Eligo el fichero a guardar
            opcion = sc.nextLine();
            //Envio el numero de fichero
            dos.writeUTF(opcion);
            //Obtengo el nombre del fichero elegido
            String filename=dis.readUTF();
            //Defino el tamaño de los datos a recibir
            receivedData = new byte[1024];
            //Creo los buffer de entrada-salida de datos
            bis = new BufferedInputStream(socketCliente.getInputStream());
            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream("C:/Users/merjan/Desktop/casa/"+filename));
            while ((in = bis.read(receivedData)) != -1){
                bos.write(receivedData,0,in);
            }
            bos.close();
            dis.close();
            socketCliente.close();
        }catch(IOException e){
            System.err.println("No puede establecer conexion");
        }
    }
}
