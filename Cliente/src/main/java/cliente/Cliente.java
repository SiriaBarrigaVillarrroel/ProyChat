/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author pc
 */
public class Cliente {

    public static void main(String[] args) {
         try {
            Socket socket = new Socket("localhost", 4444);
            System.out.println("Conectado al servidor: " + socket);

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Ciclo para enviar y recibir mensajes
            while (true) {
                System.out.print("Cliente: ");
                String mensaje = new Scanner(System.in).nextLine();
                out.println(mensaje);

                String respuesta = in.nextLine();
                System.out.println("Servidor: " + respuesta);

                // Si el servidor envía "adios", se cierra la conexión
                if (respuesta.equals("adios")) {
                    break;
                }
            }

            // Cierre de recursos
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
