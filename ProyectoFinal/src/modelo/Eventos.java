

import java.io.Serializable;
import java.util.ArrayList;

public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombreEvento;
    private String fechaEvento;
    private Locaciones lugar;
    private ArrayList<String> artistas;
    private ArrayList<Boletas> boletas;


    public Eventos() {
    }
    
    public Eventos(String nombreEvento, String fechaEvento, ArrayList<String> artistas) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.artistas = artistas;
    }

    public Eventos(String nombreEvento, String fechaEvento, Locaciones lugar, ArrayList<String> artistas,
    ArrayList<Boletas> boletas) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.lugar = lugar;
        this.artistas = artistas;
        this.boletas = boletas;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public Locaciones getLugar() {
        return lugar;
    }

    public ArrayList<String> getArtistas() {
        return artistas;
    }
    public ArrayList<Boletas> getBoletas() {
        return boletas;
    }
    
    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setArtistas(ArrayList<String> artistas) {
        this.artistas = artistas;
    }

}
