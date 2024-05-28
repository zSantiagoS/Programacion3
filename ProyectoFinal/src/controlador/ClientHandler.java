import java.io.*;
import java.net.Socket;
class ClientHandler extends Thread {
    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private EventCatalog eventCatalog;

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

                //Logica de compra de taquilla
                Usuarios usuario = request.getUsuario();
                EventCatalog eventCatalog = new EventCatalog(usuario);
                eventCatalog.setVisible(true);

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