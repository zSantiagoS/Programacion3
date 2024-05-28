import java.util.List;

public class procesoCompra {

    private Eventos eventos;
    private int cantidadBoletas;
    private String metodoPago;
    private Categorias categoria;
    private Usuarios usuario;
    

    //Constructor 
    public procesoCompra(Eventos eventos, int cantidadBoletas, String metodoPago, Categorias categoria, Usuarios usuario) {
        this.eventos = eventos;
        this.cantidadBoletas = cantidadBoletas;
        this.metodoPago = metodoPago;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    //Metodo para generar el pago total
    public double pagarTotal(){
        List<Boletas> boletas = eventos.getBoletas();
        double pagoTotalCategoria = 0;
        //Recorrer cada boleta y generar el costo 
        for (Boletas boleta : boletas) {
            if (boleta.getCategoria().equals(this.categoria)) {
                int cantidadDisponible = boleta.getCantidad();
                if (cantidadBoletas <= cantidadDisponible) {
                    double precio_unitario = boleta.getPrecio();
                    pagoTotalCategoria = precio_unitario * cantidadBoletas;
                    boleta.setCantidad(cantidadDisponible - cantidadBoletas);
                    break;
                }
                else {
                    System.out.println("No hay suficientes boletas disponibles" + boleta.getCantidad());
                }
                
            }
        }
        return pagoTotalCategoria;

    }

    //Metodo de pago 
    public void metodoPago() {
        // TODO implement here
        if (getMetodoPago().equalsIgnoreCase("tarjeta")) {
            System.out.println("Ingrese el numero de la tarjeta ");
            
        } else if (getMetodoPago().equalsIgnoreCase("tarjeta")) {
            System.out.println("ingese el valor");
        } else {
            System.out.println("Ingrese un metodo de pago");
        }  

    }


    public Eventos getEventos() {
        return eventos;
    }


    public String getMetodoPago() {
        return metodoPago;
    }

    public int getCantidadBoletas() {
        return cantidadBoletas;
    }


    public Categorias getCategoria() {
        return categoria;
    }

    public Usuarios getUsuario() {
        return usuario;
    }



    

}
