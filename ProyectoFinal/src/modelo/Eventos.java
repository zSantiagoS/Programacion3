package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Eventos {

    private String nombreEvento;
    private Date fechaEvento;
    private String lugar;
    private ArrayList<String> artistas;

    public Eventos(String nombreEvento, Date fechaEvento, String lugar, ArrayList<String> artistas) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.lugar = lugar;
        this.artistas = artistas;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public ArrayList<String> getArtistas() {
        return artistas;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setArtistas(ArrayList<String> artistas) {
        this.artistas = artistas;
    }

}
