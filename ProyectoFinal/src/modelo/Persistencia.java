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

    public List<Usuarios> leerArchivo() {
        List<Usuarios> listaUsuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("BASE_USUARIOS"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("<\\*\\*>");
                if (datos.length == 3) {
                    Usuarios usuario = new Usuarios(datos[0], datos[1], datos[2]);
                    listaUsuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public void escribirArchivo(ArrayList<Usuarios> listaUsuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("BASE_USUARIOS"))) {
            for (Usuarios usuario : listaUsuarios) {
                bw.write(usuario.getNombre() + "<**>" + usuario.getCorreo() + "<**>" + usuario.getContrasena()+ "\n");
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


