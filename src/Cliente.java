import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {

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
			while (true) {
				System.out.println("Haz preguntas de SI o NO:");
				Scanner teclado = new Scanner(System.in);
				String valor =teclado.nextLine();
				salida.writeUTF(valor);
				String respuesta=entrada.readUTF();
				System.out.println(respuesta);
				if (valor.equals("salir")) {
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
