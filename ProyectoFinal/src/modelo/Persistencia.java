import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
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
}


