import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.*;

public class EventTicketCounter extends JFrame {
    private String eventName;
    private JLabel titleLabel;
    private JLabel availabilityLabel;
    private JTextField quantityField;
    private JComboBox<String> seatTypeComboBox;
    private JButton buyButton;

    public EventTicketCounter(String eventName) {
        this.eventName = eventName;

        setTitle("Taquilla Virtual - " + eventName);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        titleLabel = new JLabel("Evento: " + eventName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(33, 150, 243));

        availabilityLabel = new JLabel("Boletas disponibles: 100", SwingConstants.CENTER); // Actualizar con la disponibilidad real
        availabilityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        quantityField = new JTextField(10);
        
        String[] seatTypes = {"Oro", "Plata", "Bronce"};
        seatTypeComboBox = new JComboBox<>(seatTypes);

        buyButton = new JButton("Comprar");
        buyButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        buyButton.setBackground(new Color(33, 150, 243));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFocusPainted(false);

        // Configurar el panel principal con GridBagLayout para mejor control de los componentes
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Añadir componentes al panel principal
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridy++;
        mainPanel.add(availabilityLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Cantidad de Boletas:"), gbc);

        gbc.gridx = 2;
        mainPanel.add(quantityField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        mainPanel.add(new JLabel("Tipo de Asiento:"), gbc);

        gbc.gridx = 2;
        mainPanel.add(seatTypeComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(buyButton, gbc);

        // Crear el panel del teatro con la representación visual de los asientos
        JPanel theaterPanel = createTheaterPanel();
        
        // Añadir los paneles al frame
        getContentPane().setLayout(new GridLayout(1, 2));
        add(theaterPanel);
        add(mainPanel);



        // Configurar el botón de compra
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si hay espacio en la cola para el cliente actual
                if (checkQueueCapacity()) {
                    int quantity = Integer.parseInt(quantityField.getText());
                    String seatType = (String) seatTypeComboBox.getSelectedItem();
                    // Simular una compra exitosa
                    if (purchaseTickets(quantity, seatType)) {
                        // Mostrar la interfaz de pago
                        PaymentInterface paymentInterface = new PaymentInterface();
                        paymentInterface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        paymentInterface.setVisible(true);
                        // Cerrar la ventana de la taquilla después de la compra
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo completar la compra. Inténtalo de nuevo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cola de ingreso está llena. Por favor, espere su turno.");
                }
            }
        });
    }

    private JPanel createTheaterPanel() {
        JPanel theaterPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int totalSeats = 100;
                int goldSeats = (int) (totalSeats * 0.10);
                int silverSeats = (int) (totalSeats * 0.30);
                int bronzeSeats = totalSeats - goldSeats - silverSeats;

                int seatWidth = 20;
                int seatHeight = 20;
                int startX = 50;
                int startY = 50;
                int gap = 5;

                int row = 0, col = 0;

                for (int i = 0; i < totalSeats; i++) {
                    if (i < goldSeats) {
                        g.setColor(Color.YELLOW);
                    } else if (i < goldSeats + silverSeats) {
                        g.setColor(Color.LIGHT_GRAY);
                    } else {
                        g.setColor(Color.ORANGE);
                    }

                    g.fillRect(startX + col * (seatWidth + gap), startY + row * (seatHeight + gap), seatWidth, seatHeight);

                    col++;
                    if (col >= 10) {
                        col = 0;
                        row++;
                    }
                }

                // Dibujar la tarima
                g.setColor(Color.DARK_GRAY);
                g.fillRect(startX, startY - 30, 10 * (seatWidth + gap) - gap, 20);
                g.setColor(Color.WHITE);
                g.drawString("Tarima", startX + (10 * (seatWidth + gap) - gap) / 2 - 20, startY - 15);
            }
        };

        theaterPanel.setPreferredSize(new Dimension(300, 300));
        theaterPanel.setBorder(BorderFactory.createTitledBorder("Teatro"));
        return theaterPanel;
    }
    

    private boolean checkQueueCapacity() {
        // Utilizar un semáforo para controlar el acceso a la cola de ingreso
        Semaphore semaphore = new Semaphore(3); // Permitir hasta 3 clientes simultáneamente
        try {
            semaphore.acquire(); // Intentar adquirir un permiso del semáforo
            // Permitir al cliente continuar con la compra
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            semaphore.release(); // Liberar el permiso adquirido
        }
    }

    private boolean purchaseTickets(int quantity, String seatType) {
        // Simular la generación de boletos electrónicos con el tipo de asiento
        try {
            for (int i = 0; i < quantity; i++) {
                String fileName = "ticket_" + i + "_" + seatType + ".pdf";
                System.out.println("Boleto electrónico generado: " + fileName);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Método main para ejemplo de uso
    public static void main(String[] args) {
        EventTicketCounter ticketCounter = new EventTicketCounter("Concierto de Jazz");
        ticketCounter.setVisible(true);
    }
}
