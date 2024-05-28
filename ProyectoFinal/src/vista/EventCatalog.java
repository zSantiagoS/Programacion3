import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventCatalog extends JFrame {
    private JTable eventTable;
    private DefaultTableModel tableModel;

    public EventCatalog() {
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
                    openTicketCounter(eventName);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor selecciona un evento.");
                }
            }
        });

        // Agregar algunos datos de ejemplo
        tableModel.addRow(new Object[]{"Concierto Rock", "2023-08-15", "Estadio Central", "Banda XYZ"});
        tableModel.addRow(new Object[]{"Festival de Jazz", "2023-09-10", "Auditorio Nacional", "Artista ABC"});
    }

    private void openTicketCounter(String eventName) {
        // Abrir la taquilla virtual para el evento seleccionado
        EventTicketCounter ticketCounter = new EventTicketCounter(eventName);
        ticketCounter.setVisible(true);
    }

    public static void main(String[] args) {
        EventCatalog catalog = new EventCatalog();
        catalog.setVisible(true);
    }
}

class EventTicketCounter extends JFrame {
    public EventTicketCounter(String eventName) {
        setTitle("Taquilla - " + eventName);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Comprar boletas para: " + eventName);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.CENTER);
    }
}

