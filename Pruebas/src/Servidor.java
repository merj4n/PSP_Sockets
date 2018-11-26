import java.net.*;
import java.io.*;

public class Servidor {

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
				int azar = (int) (Math.random()*1000)+7000;
				cadena = entrada.readUTF();
				if (cadena.equals("adios")) {
					salida.writeUTF("Adios amigo!");
					break;
				}else if (azar%2==0){
					salida.writeUTF("SI");
					System.out.println(cadena);
				}else{
					salida.writeUTF("NO");
					System.out.println(cadena);
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
