import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientHandler extends Thread {
    private static final Logger log = Logger.getLogger(ClientHandler.class.getName());

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

            log.info("Flujos de E/S creados para la comunicación con el cliente.");

            // Esperar y manejar la solicitud del cliente
            Object receivedObject = objectInputStream.readObject();
            if (receivedObject instanceof LoginRequest) {
                LoginRequest request = (LoginRequest) receivedObject;

                log.info("Solicitud de inicio de sesión recibida: " + request);

                // Lógica de compra de taquilla
                Usuarios usuario = request.getUsuario();
                EventCatalog eventCatalog = new EventCatalog(usuario);
                eventCatalog.setVisible(true);

                log.info("Evento de catálogo mostrado para el usuario: " + usuario.getNombre());

                // Por simplicidad, se enviará una respuesta de éxito al cliente
                LoginResponse response = new LoginResponse(true, "Inicio de sesión exitoso");

                // Enviar la respuesta al cliente
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();

                log.info("Respuesta de inicio de sesión exitosa enviada al cliente.");
            } else {
                // Manejar error si la solicitud no es del tipo esperado
                log.warning("Error: solicitud no válida del cliente.");
            }
        } catch (IOException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Error al manejar la solicitud del cliente.", e);
        } finally {
            try {
                // Cerrar flujos y conexión con el cliente
                if (objectInputStream != null) objectInputStream.close();
                if (objectOutputStream != null) objectOutputStream.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                log.log(Level.SEVERE, "Error al cerrar los recursos.", e);
            }
        }
    }
}
