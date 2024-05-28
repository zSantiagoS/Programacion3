public class controlProcesoCompra {

    private Eventos evento;
    private PaymentInterface paymentInterface;
    private EventTicketCounter eventCounter;
    private procesoCompra compraProceso;
    private Persistencia p;
    
    
    public controlProcesoCompra(Eventos evento) {
        this.evento = evento;
        p = Persistencia.getInstance();
        eventCounter = EventTicketCounter.getInstance(evento.getNombreEvento());
        paymentInterface = PaymentInterface.getInstance();
    }

    //Accion del boton de eventoCounter de comprar
    public void comprar() {

    }
    

    




    

}
