import java.io.*;
import java.net.Socket;

public class EventClient {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public EventClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendRequest(Object request) throws IOException {
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
    }

    public Object receiveResponse() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    public void close() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        socket.close();
    }
}
