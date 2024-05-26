package modelo;

public class Boletas {
    private Categorias categoria;
    private double precio;
    private int disponibilidad;

    // Constructor
    public Boletas(Categorias categoria, double precio, int disponibilidad) {
        this.categoria = categoria;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    // Getters
    public Categorias getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }


    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // Método para verificar la disponibilidad de boletas
    public boolean hayDisponibilidad() {
        return disponibilidad > 0;
    }

    // Método para actualizar la disponibilidad después de una compra
    public void reducirDisponibilidad(int cantidad) {
        if (cantidad <= disponibilidad) {
            disponibilidad -= cantidad;
        }
    }
}
