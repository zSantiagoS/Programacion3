import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EventServer {
    private ServerSocket serverSocket;

    public EventServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Servidor iniciado. Esperando conexiones...");

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Crear un nuevo hilo para manejar la conexión con el cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EventServer server = new EventServer(8080);
        server.start();
    }
}

