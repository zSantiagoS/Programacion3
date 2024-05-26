package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Eventos {

    private String nombreEvento;
    private Date fechaEvento;
    private String lugar;
    private ArrayList<String> artistas;
    private Map<Categorias, Double> precioBoletas;

    public Eventos(String nombreEvento, Date fechaEvento, String lugar, ArrayList<String> artistas,
            Map<Categorias, Double> precioBoletas) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.lugar = lugar;
        this.artistas = artistas;
        this.precioBoletas = precioBoletas;
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

    public Map<Categorias, Double> getPrecioBoletas() {
        return precioBoletas;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setArtistas(ArrayList<String> artistas) {
        this.artistas = artistas;
    }

}
