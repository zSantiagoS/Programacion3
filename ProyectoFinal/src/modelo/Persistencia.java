import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Persistencia {
    private static Persistencia instancia;

    private Persistencia() {

    }

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
    
    public void escribirArchivoEventos(List<Object> listaObjetos) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("BASE_EVENTOS.xml")))) {
            encoder.writeObject(listaObjetos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Guadar transaccion
    public void guadarTransaccion(double pagoTotal, Usuarios usuario, Eventos evento, Categorias categoria,
            String metodoPago, int cantidadBoletas) {

        try {

            // Verifica si el directorio existe, si no, lo crea
            File directorio = new File("./Transacciones");
            if (!directorio.exists()) {
                directorio.mkdir();
            }

            // Crear archivo en el directorio Transacciones
            File archivo = new File(directorio, "transaccion.txt");


            //Se habre el archivo en modo append para ir agregando datos 
            FileWriter escritor = new FileWriter(archivo, true);
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);

            Date fechaActual = new Date();

            //Detalles de la transaccion 
            bufferEscritor.write("Detalles de la transacción:");
            bufferEscritor.newLine();
            bufferEscritor.write("Fecha: " + usuario.getNombre());
            bufferEscritor.newLine();
            bufferEscritor.write("Nombre del usuario: " + fechaActual.toString());
            bufferEscritor.newLine();
            bufferEscritor.write("Evento: " + evento.getNombreEvento());
            bufferEscritor.newLine();
            bufferEscritor.write("Cantidad de boletas: " + cantidadBoletas);
            bufferEscritor.newLine();
            bufferEscritor.write("Categoría: " + categoria);
            bufferEscritor.newLine();
            bufferEscritor.write("Método de pago: " + metodoPago);
            bufferEscritor.newLine();
            bufferEscritor.write("Precio total: " + pagoTotal);

            bufferEscritor.close();
            escritor.close();

            System.out.println("Transacción guardada en el archivo 'transaccion.txt'.");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
