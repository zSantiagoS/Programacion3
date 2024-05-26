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
    private JTextField nameField; // Campo para el nombre del comprador
    private JTextField idField; // Campo para la identificación del comprador
    private JButton buyButton;

    public EventTicketCounter(String eventName) {
        this.eventName = eventName;

        setTitle("Taquilla Virtual - " + eventName);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        titleLabel = new JLabel("Evento: " + eventName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(33, 150, 243));

        availabilityLabel = new JLabel("Boletas disponibles: 100", SwingConstants.CENTER); // Actualizar con la disponibilidad real
        availabilityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        quantityField = new JTextField(10);
        nameField = new JTextField(10);
        idField = new JTextField(10);
        
        buyButton = new JButton("Comprar");
        buyButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        buyButton.setBackground(new Color(33, 150, 243));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFocusPainted(false);

        // Configurar el panel principal con GridBagLayout para mejor control de los componentes
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Añadir componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy++;
        panel.add(availabilityLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Cantidad de Boletas:"), gbc);

        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Identificación:"), gbc);

        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(buyButton, gbc);

        // Agregar el panel al frame
        add(panel);

        // Actualizar la disponibilidad de boletas al abrir la ventana
        updateAvailability();

        // Verificar y cerrar la taquilla automáticamente una hora antes del evento
        closeTicketCounter();

        // Configurar el botón de compra
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si hay espacio en la cola para el cliente actual
                if (checkQueueCapacity()) {
                    int quantity = Integer.parseInt(quantityField.getText());
                    String name = nameField.getText(); // Obtener el nombre del comprador
                    String id = idField.getText(); // Obtener la identificación del comprador
                    // Simular una compra exitosa
                    if (purchaseTickets(quantity, name, id)) {
                        JOptionPane.showMessageDialog(null, "Compra exitosa. Se han generado los boletos electrónicos.");
                        dispose(); // Cerrar la ventana después de la compra
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo completar la compra. Inténtalo de nuevo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cola de ingreso está llena. Por favor, espere su turno.");
                }
            }
        });
    }

    private void updateAvailability() {
        try (Socket socket = new Socket("localhost", 9091)) {
            // Leer la disponibilidad de boletas del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int availability = Integer.parseInt(in.readLine());
            availabilityLabel.setText("Boletas disponibles: " + availability);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeTicketCounter() {
        // Obtener la hora actual y la hora del evento (simulada)
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime eventTime = LocalDateTime.of(2024, Month.MAY, 20, 19, 0); // Ejemplo: Evento a las 19:00 el 20 de mayo de 2024

        // Calcular la diferencia de tiempo en horas entre la hora actual y la hora del evento
        long hoursUntilEvent = Duration.between(currentTime, eventTime).toHours();

        // Si queda menos de una hora para el evento, cerrar la taquilla
        if (hoursUntilEvent < 1) {
            buyButton.setEnabled(false);
            quantityField.setEnabled(false);
            nameField.setEnabled(false); // Deshabilitar el campo de nombre
            idField.setEnabled(false); // Deshabilitar el campo de identificación
            JOptionPane.showMessageDialog(null, "La taquilla se ha cerrado. Ya no se pueden realizar compras.");
        }
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

    private boolean purchaseTickets(int quantity, String name, String id) {
        // Simular la generación de boletos electrónicos con nombre e identificación del comprador
        try {
            // Crear un archivo PDF para cada boleto con el nombre e identificación del comprador
            for (int i = 0; i < quantity; i++) {
                String fileName = "ticket_" + i + "_" + name + "_" + id + ".pdf";
                // Aquí puedes agregar la lógica para generar el boleto en PDF con el nombre e identificación del comprador
                System.out.println("Boleto electrónico generado: " + fileName);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        EventTicketCounter ticketCounter = new EventTicketCounter("Concierto de Jazz");
        ticketCounter.setVisible(true);
    }
}
