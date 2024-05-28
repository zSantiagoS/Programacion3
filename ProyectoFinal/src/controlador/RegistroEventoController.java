import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegistroEventoController {
    private RegisterEventView registerEventView;
    private ArrayList<Eventos> listaEventos;
    private Persistencia p;

    public RegistroEventoController(RegisterEventView registerEventView) {
        this.p = Persistencia.getInstance();
        this.listaEventos = p.leerArchivoEventos();
        this.registerEventView = registerEventView;

        // Asociar botones
        this.registerEventView.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    public void handleRegister() {
       RegisterEventView registerEventView = new RegisterEventView(this);
       registerEventView.setVisible(true);
    }

    public void addEvento(Eventos evento) {
        if (!eventoExiste(evento.getNombreEvento())) {
            listaEventos.add(evento);
            p.escribirArchivoEventos(listaEventos);
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Evento registrado exitosamente");
                registerEventView.clearFields();
            });
        } else {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "El evento con el nombre " + evento.getNombreEvento() + " YA existe.");
            });
        }
    }

    public boolean eventoExiste(String nombre) {
        for (Eventos evento : listaEventos) {
            if (evento.getNombreEvento().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Eventos> getListaEventos() {
        return listaEventos;
    }
}

