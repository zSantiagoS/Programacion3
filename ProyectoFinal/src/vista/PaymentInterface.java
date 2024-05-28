import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentInterface extends JFrame {
    private JButton payButton;
    private JRadioButton creditCardRadioButton;
    private JRadioButton cashRadioButton;
    private JTextField cardNumberField;
    private static PaymentInterface instancia;
    private JTextField totalCostField;


    public static PaymentInterface getInstance() {
        if (instancia == null) {
            instancia = new PaymentInterface();
        }
        return instancia;
    }

    public PaymentInterface() {
        setTitle("Método de Pago");
        setSize(400, 300); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel paymentLabel = new JLabel("Seleccione el método de pago:");
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 18));

        creditCardRadioButton = new JRadioButton("Tarjeta de Crédito");
        creditCardRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cashRadioButton = new JRadioButton("Efectivo");
        cashRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        ButtonGroup paymentMethodGroup = new ButtonGroup();
        paymentMethodGroup.add(creditCardRadioButton);
        paymentMethodGroup.add(cashRadioButton);

        cardNumberField = new JTextField(15);
        cardNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
        cardNumberField.setEnabled(false);

        totalCostField = new JTextField(15); 
        totalCostField.setFont(new Font("Arial", Font.PLAIN, 16));
        totalCostField.setEditable(false); 
        totalCostField.setBackground(Color.WHITE); 
        totalCostField.setText("$50.00"); // Ejemplo de costo total

        payButton = new JButton("Pagar");
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setEnabled(false);
        payButton.setBackground(new Color(65, 105, 225));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);
        payButton.setBorderPainted(false);
        payButton.setPreferredSize(new Dimension(100, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(paymentLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(creditCardRadioButton, gbc);

        gbc.gridy++;
        panel.add(cashRadioButton, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cardNumberField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Costo total:"), gbc); // Nuevo campo para mostrar el costo total

        gbc.gridy++;
        panel.add(totalCostField, gbc); 

        gbc.gridy++;
        panel.add(payButton, gbc);

        // Eventos de los botones de radio
        creditCardRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardNumberField.setEnabled(true);
                payButton.setEnabled(true);
            }
        });

        cashRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardNumberField.setEnabled(false);
                payButton.setEnabled(true);
            }
        });

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "¡El pago se ha procesado con éxito!\n\n" +
                        "Se ha enviado la información al correo electrónico.", "Confirmación de Pago", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Usa el aspecto y la sensación del sistema
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                PaymentInterface paymentInterface = new PaymentInterface();
                paymentInterface.setVisible(true);
            }
        });
    }
}
