import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
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
            if (boleta.getCategoria().equals(categoria)) {
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

    //Guadar transaccion 
    public void guadarTransaccion(double pagoTotal){
        
        try {

            File archivo = new File("./Transacciones"+"transaccion_" + getUsuario().getNombre() + ".txt");

            FileWriter escritor = new FileWriter(archivo);
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);

            Date fechaActual = new Date();

            bufferEscritor.write("Detalles de la transacción:");
            bufferEscritor.newLine();
            bufferEscritor.write("Fecha: " + fechaActual.toString());
            bufferEscritor.newLine();
            bufferEscritor.write("Evento: " + eventos.getNombreEvento());
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
