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
    private static EventTicketCounter instancia;

    public static EventTicketCounter getInstance(String eventName) {
        if (instancia == null) {
            instancia = new EventTicketCounter(eventName);
        }
        return instancia;
    }

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
    
    
    public String getEventName() {
        return eventName;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getAvailabilityLabel() {
        return availabilityLabel;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JComboBox<String> getSeatTypeComboBox() {
        return seatTypeComboBox;
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    // Método main para ejemplo de uso
    public static void main(String[] args) {
        EventTicketCounter ticketCounter = new EventTicketCounter("Concierto de Jazz");
        ticketCounter.setVisible(true);
    }
}
