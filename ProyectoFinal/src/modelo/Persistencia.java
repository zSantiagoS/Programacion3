import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static Persistencia instancia;

    private Persistencia() {}

    public static Persistencia getInstance() {
        if (instancia == null) {
            instancia = new Persistencia();
        }
        return instancia;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Usuarios> leerArchivo() {
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("BASE_USUARIOS.xml")))) {
            listaUsuarios = (ArrayList<Usuarios>) decoder.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("El archivo BASE_USUARIOS.xml no existe todavía.");
        }
        return listaUsuarios;
    }

    public void escribirArchivo(List<Usuarios> listaObjetos) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("BASE_USUARIOS.xml")))) {
            encoder.writeObject(listaObjetos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

   
    @SuppressWarnings("unchecked")
    public ArrayList<Eventos> leerArchivoEventos() {
        ArrayList<Eventos> listaEventos = new ArrayList<>();
        File eventosFile = new File("BASE_EVENTOS.xml");
        
        if (eventosFile.exists() && eventosFile.isFile()) {
            try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(eventosFile)))) {
                listaEventos = (ArrayList<Eventos>) decoder.readObject();
            } catch (FileNotFoundException e) {
                System.err.println("El archivo BASE_EVENTOS.xml no existe todavía.");
            } catch (Exception e) {
                System.err.println("Error al leer el archivo BASE_EVENTOS.xml: " + e.getMessage());
            }
        } else {
            System.err.println("El archivo BASE_EVENTOS.xml está vacío o no existe.");
        }
        
        return listaEventos;
    }

    public void escribirArchivoEventos(List<Eventos> listaEventos) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("BASE_EVENTOS.xml")))) {
            encoder.writeObject(listaEventos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guadarTransaccion(double pagoTotal, Usuarios usuario, Eventos evento, String metodoPago, int cantidadBoletas) {
        try {
            File directorio = new File("./Transacciones");
            if (!directorio.exists()) {
                directorio.mkdir();
            }

            File archivo = new File(directorio, "transaccion.txt");

            FileWriter escritor = new FileWriter(archivo, true);
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);

            java.util.Date fechaActual = new java.util.Date();

            bufferEscritor.write("Detalles de la transacción:");
            bufferEscritor.newLine();
            bufferEscritor.write("Fecha: " + fechaActual.toString());
            bufferEscritor.newLine();
            bufferEscritor.write("Nombre del usuario: " + usuario.getNombre());
            bufferEscritor.newLine();
            bufferEscritor.write("Evento: " + evento.getNombreEvento());
            bufferEscritor.newLine();
            bufferEscritor.write("Cantidad de boletas: " + cantidadBoletas);
            bufferEscritor.newLine();
            bufferEscritor.write("Método de pago: " + metodoPago);
            bufferEscritor.newLine();
            bufferEscritor.write("Precio total: " + pagoTotal);
            bufferEscritor.newLine();

            bufferEscritor.close();
            escritor.close();

            System.out.println("Transacción guardada en el archivo 'transaccion.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
