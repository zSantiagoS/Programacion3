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

    public controlProcesoCompra(Eventos evento, Semaphore semaphoreEventos) {
        this.evento = evento;
        this.p = Persistencia.getInstance();
        this.eventCounter = EventTicketCounter.getInstance(evento.getNombreEvento());
        this.paymentInterface = PaymentInterface.getInstance();
        this.semaphoreEventos = semaphoreEventos;

        // Configurar el botón de compra en EventTicketCounter
        this.eventCounter.getBuyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si hay semaforos de hilo disponible
                if (semaphoreEventos.availablePermits() > 0) {
                    // Verifica si esos semaforos se puede adquirir
                    if (semaphoreEventos.tryAcquire()) {
                        botonCompraPresionado();

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "La cola de ingreso está llena. Por favor, espere su turno.");
                    }
                }
            }
        });

    }

    public void botonCompraPresionado(){
        //Obtener los datos ingresados por el panel
        int cantidadBoletas = Integer.parseInt(eventCounter.getQuantityField().getText());
        Categorias categoria = (Categorias) eventCounter.getSeatTypeComboBox().getSelectedItem();
        
        procesoCompra procesoCompra = new procesoCompra(evento, cantidadBoletas, null, categoria, null);


        
    }

}
