import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

public class controlProcesoCompra {

    private Eventos evento;
    private PaymentInterface paymentInterface;
    private EventTicketCounter eventCounter;
    private procesoCompra compraProceso;
    private Persistencia p;
    private Semaphore semaphoreEventos;
    private EventCatalog eventCatalog;
    private Usuarios usuario;

    public controlProcesoCompra(Eventos evento, Semaphore semaphoreEventos, Usuarios usuario) {
        this.evento = evento;
        this.p = Persistencia.getInstance();
        this.eventCounter = EventTicketCounter.getInstance(evento.getNombreEvento());
        this.semaphoreEventos = semaphoreEventos;
        this.usuario = usuario;

        // Abrir ventana de seleccion de tiquetes
        this.eventCounter.setVisible(true);


        
        // Configurar el botón de compra en EventTicketCounter
        this.eventCounter.getBuyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("paso");
                botonCompraPresionado();
            }
        });

    }

    public void botonCompraPresionado() {
        // Obtener los datos ingresados por el panel
        int cantidadBoletas = Integer.parseInt(eventCounter.getQuantityField().getText());
        String nomCategoria = (String) eventCounter.getSeatTypeComboBox().getSelectedItem();

        Categorias categoria = Categorias.valueOf(nomCategoria.toUpperCase());
        System.out.println(categoria);

        procesoCompra procesoCompra = new procesoCompra(evento, cantidadBoletas, null, categoria, usuario);

        Double costoTotal = 32.50;

        String stringCostoTotal = String.valueOf(costoTotal);

        // Abrir la ventana de pago
        paymentInterface = new PaymentInterface(stringCostoTotal);
        paymentInterface.setVisible(true);

    }

}
