import javax.swing.*;
import java.awt.*;
public class RegisterEventView extends JFrame {

    private JTextField eventField;
    private JTextField dateField;
    private JComboBox<String> categoryComboBox;
    private JButton registerButton;

    public RegisterEventView() {
        setTitle("Registrar Evento");
        setSize(400, 300); // Aumenté la altura para acomodar más componentes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registrar Nuevo Evento");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel eventLabel = new JLabel("Nombre del Evento:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(eventLabel, gbc);

        eventField = new JTextField(20); // Corregido para usar el campo de instancia
        gbc.gridx = 1;
        panel.add(eventField, gbc);

        JLabel dateLabel = new JLabel("Fecha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dateLabel, gbc);

        dateField = new JTextField(10); // Corregido para usar el campo de instancia
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        JLabel categoryLabel = new JLabel("Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(categoryLabel, gbc);

        String[] categories = {"Categoria 1", "Categoria 2", "Categoria 3"}; // Ejemplo de opciones para el ComboBox
        categoryComboBox = new JComboBox<>(categories);
        gbc.gridx = 1;
        panel.add(categoryComboBox, gbc);

        registerButton = new JButton("Registrar"); // Corregido para usar el campo de instancia
        registerButton.setBackground(new Color(33, 150, 243));
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        panel.add(registerButton, gbc);

        add(panel);
    }

    public JTextField getEventField() {
        return eventField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

}
