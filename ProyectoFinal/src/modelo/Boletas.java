
public class Boletas {
    private Categorias categoria;
    private int cantidad;
    private double precio;

    // Constructor
    public Boletas(Categorias categoria, int cantidad, double precio) {
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }


    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }


    public double getPrecio() {
        return precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }


    // Getters
    public Categorias getCategoria() {
        return categoria;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método para verificar la cantidad de boletas
    public boolean haycantidad() {
        return cantidad > 0;
    }

    // Método para actualizar la cantidad después de una compra
    public void reducircantidad(int cantidad) {
        if (cantidad <= cantidad) {
            cantidad -= cantidad;
        }
    }

}
