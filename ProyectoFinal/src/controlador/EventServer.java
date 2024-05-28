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

class ClientHandler extends Thread {
    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Crear flujos de entrada/salida para la comunicación con el cliente
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            // Esperar y manejar la solicitud del cliente
            Object receivedObject = objectInputStream.readObject();
            if (receivedObject instanceof LoginRequest) {
                LoginRequest request = (LoginRequest) receivedObject;

                // Por simplicidad, se enviará una respuesta de éxito al cliente
                LoginResponse response = new LoginResponse(true, "Inicio de sesión exitoso");

                // Enviar la respuesta al cliente
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();
            } else {
                // Manejar error si la solicitud no es del tipo esperado
                System.out.println("Error: solicitud no válida del cliente.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar flujos y conexión con el cliente
                if (objectInputStream != null) objectInputStream.close();
                if (objectOutputStream != null) objectOutputStream.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
