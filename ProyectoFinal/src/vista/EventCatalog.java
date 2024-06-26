import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EventCatalog extends JFrame {
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private Persistencia p;
    private static EventCatalog instancia;
    private Usuarios usuario;
    private controlProcesoCompra controlProcesoCompra;

    public static EventCatalog getInstance(Usuarios usuario) {
        if (instancia == null) {
            instancia = new EventCatalog(usuario);
        }
        return instancia;
    }

    public EventCatalog(Usuarios usuario) {
        p = Persistencia.getInstance();
        setTitle("Catálogo de Eventos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre del Evento");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Lugar");
        tableModel.addColumn("Artistas");

        // Crear la tabla
        eventTable = new JTable(tableModel);
        eventTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        eventTable.setRowHeight(30);
        eventTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        eventTable.getTableHeader().setBackground(new Color(33, 150, 243));
        eventTable.getTableHeader().setForeground(Color.WHITE);

        // Agregar la tabla a un scroll pane
        JScrollPane scrollPane = new JScrollPane(eventTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Agregar botón "Comprar Boletas"
        JButton buyButton = new JButton("Comprar Boletas");
        buyButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        buyButton.setBackground(new Color(33, 150, 243));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFocusPainted(false);
        buyButton.setPreferredSize(new Dimension(200, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(buyButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Manejar clic en el botón "Comprar Boletas"
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {

                    String eventName = (String) eventTable.getValueAt(selectedRow, 0);
                    String fecha = (String) eventTable.getValueAt(selectedRow, 1);
                    String lugar = (String)eventTable.getValueAt(selectedRow, 2);
                    @SuppressWarnings("unchecked")
                    ArrayList<String> artistas = (ArrayList<String>) eventTable.getValueAt(selectedRow, 3);
                    Eventos evento = new Eventos(eventName, fecha, artistas, lugar);
                    new controlProcesoCompra(evento, null, usuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor selecciona un evento.");
                }
            }
        });

        ArrayList<Eventos> eventos = p.leerArchivoEventos();
        for (Eventos evento : eventos) {
            // Puedes modificar cómo se muestra cada evento en la tabla según tus necesidades
            tableModel.addRow(new Object[]{(String)evento.getNombreEvento(), evento.getFechaEvento(), (String)evento.getLugar() , evento.getArtistas()});
        }
        // Agregar algunos datos de ejemplo
        /*tableModel.addRow(new Object[]{"Concierto Rock", "2023-08-15", "Estadio Central", "Banda XYZ"});
        tableModel.addRow(new Object[]{"Festival de Jazz", "2023-09-10", "Auditorio Nacional", "Artista ABC"});
        tableModel.addRow(new Object[]{"Locos del barrio", "2023-08-15", "Mis patas", "Banda 121321"});*/

    }
    

    public JTable getEventTable() {
        return eventTable;
    }

}


