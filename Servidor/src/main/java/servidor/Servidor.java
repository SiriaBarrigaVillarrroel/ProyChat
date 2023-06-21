/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package servidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author pc
 */
public class Servidor {

    public static void main(String[] args) {
         try {
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Servidor listo para aceptar conexiones...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket);

            Scanner in = new Scanner(clientSocket.getInputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Ciclo para recibir y enviar mensajes
            while (true) {
                String mensaje = in.nextLine();
                System.out.println("Cliente: " + mensaje);

                // Si el cliente envía "adios", se cierra la conexión
                if (mensaje.equals("adios")) {
                    break;
                }

                System.out.print("Servidor: ");
                String respuesta = new Scanner(System.in).nextLine();
                out.println(respuesta);
            }

            // Cierre de recursos
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
