import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class RegistroEventoController {
    
    private RegisterEventView registerEventView;
    private ArrayList<Eventos> listaEventos;
    private Persistencia p;

    public RegistroEventoController(RegisterEventView registerEventView) {
        p = Persistencia.getInstance();


        this.registerEventView.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }

        });

    }
    
    public void handleRegister() {
        String nombreEvento = registerEventView.getEventField().getText();
        Date fechaEvento;
        ArrayList<String> listaArtistas;
    }
}
